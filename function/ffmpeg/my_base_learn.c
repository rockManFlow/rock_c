/**
 * 简单的打开-读取-解码-关闭流程
 */
#include "../../include/ffmpeg/win/libavformat/avformat.h"
#include "../../include/ffmpeg/win/libavcodec/avcodec.h"
static void print_video_format(const AVFrame *frame) {
    printf("width: %u\n", frame->width);
    printf("height: %u\n", frame->height);
    printf("format: %u\n", frame->format);  // 格式需要注意
}

static void print_sample_format(const AVFrame *frame) {
    printf("ar-samplerate: %uHz\n", frame->sample_rate);
//    printf("ac-channel: %u\n", frame->channels);
    printf("f-format: %u\n",
           frame->format);  // 格式需要注意，实际存储到本地文件时已经改成交错模式
}


static void decode(AVCodecContext *dec_ctx, AVPacket *pkt, AVFrame *frame,
                   FILE *outfile) {
    int ret;
    /* send the packet with the compressed data to the decoder 解码AVPacket*/
    ret = avcodec_send_packet(dec_ctx, pkt);
    if (ret == AVERROR(EAGAIN)) {
        fprintf(stderr,
                "Receive_frame and send_packet both returned EAGAIN, which is an "
                "API violation.\n");
    } else if (ret < 0) {
        fprintf(stderr,
                "Error submitting the packet to the decoder, err:%s, pkt_size:%d\n",
                ret, pkt->size);
        return;
    }

    /* read all the output frames (infile general there may be any number of them
     */
    while (ret >= 0) {
        // 对于frame, avcodec_receive_frame内部每次都先调用，接收解码后的源数据到 AVFrame
        ret = avcodec_receive_frame(dec_ctx, frame);
        if (ret == AVERROR(EAGAIN) || ret == AVERROR_EOF)
            return;
        else if (ret < 0) {
            fprintf(stderr, "Error during decoding\n");
            exit(1);
        }
        static int s_print_format = 0;
        if (s_print_format == 0) {
            s_print_format = 1;
            print_video_format(frame);
        }

        // 一般H264默认为 AV_PIX_FMT_YUV420P, 具体怎么强制转为 AV_PIX_FMT_YUV420P
        // 在音视频合成输出的时候讲解 frame->linesize[1]  对齐的问题 正确写法
        // linesize[]代表每行的字节数量，所以每行的偏移是linesize[]
        for (int j = 0; j < frame->height; j++)
            fwrite(frame->data[0] + j * frame->linesize[0], 1, frame->width, outfile);
        for (int j = 0; j < frame->height / 2; j++)
            fwrite(frame->data[1] + j * frame->linesize[1], 1, frame->width / 2,
                   outfile);
        for (int j = 0; j < frame->height / 2; j++)
            fwrite(frame->data[2] + j * frame->linesize[2], 1, frame->width / 2,
                   outfile);
    }
}

static void decodeAudio(AVCodecContext *dec_ctx, AVPacket *pkt, AVFrame *frame,
                   FILE *outfile) {
    int i, ch;
    int ret, data_size;
    /* send the packet with the compressed data to the decoder */
    ret = avcodec_send_packet(dec_ctx, pkt);
    if (ret == AVERROR(EAGAIN)) {
        fprintf(stderr,
                "Receive_frame and send_packet both returned EAGAIN, which is an "
                "API violation.\n");
    } else if (ret < 0) {
        fprintf(stderr,
                "Error submitting the packet to the decoder, err:%s, pkt_size:%d\n",
                ret, pkt->size);
        return;
    }

    /* read all the output frames (infile general there may be any number of them
     */
    while (ret >= 0) {
        // 对于frame, avcodec_receive_frame内部每次都先调用
        ret = avcodec_receive_frame(dec_ctx, frame);
        if (ret == AVERROR(EAGAIN) || ret == AVERROR_EOF)
            return;
        else if (ret < 0) {
            fprintf(stderr, "Error during decoding\n");
            exit(1);
        }
        data_size = av_get_bytes_per_sample(dec_ctx->sample_fmt);
        if (data_size < 0) {
            /* This should not occur, checking just for paranoia */
            fprintf(stderr, "Failed to calculate data size\n");
            exit(1);
        }
        static int s_print_format = 0;
        if (s_print_format == 0) {
            s_print_format = 1;
            print_sample_format(frame);
        }
        /**
            P表示Planar（平面），其数据格式排列方式为 :
            LLLLLLRRRRRRLLLLLLRRRRRRLLLLLLRRRRRRL...（每个LLLLLLRRRRRR为一个音频帧）
            而不带P的数据格式（即交错排列）排列方式为：
            LRLRLRLRLRLRLRLRLRLRLRLRLRLRLRLRLRLRL...（每个LR为一个音频样本）
         播放范例：   ffplay -ar 48000 -ac 2 -f f32le believe.pcm
          */
        for (i = 0; i < frame->nb_samples; i++) {
            for (ch = 0; ch < dec_ctx->channels;
                 ch++)  // 交错的方式写入, 大部分float的格式输出
                fwrite(frame->data[ch] + data_size * i, 1, data_size, outfile);
        }
    }
}

int main(){
    //这将注册所有的FFmpeg组件，以便在后续的操作中能够正确地使用它们。
//    av_demuxer_iterate();

    const char* video = "D:\\myProjects\\rock_c\\source\\test.mp4";
    FILE *outfile=fopen("D:\\myProjects\\rock_c\\source\\1234.yuv","w+b");
    //av格式上下文
    AVFormatContext *fmt_ctx = avformat_alloc_context();;
    //av视频包数据
    AVPacket *packet= av_packet_alloc();
    int ret;
    //todo 1、这个类似于解封装：把mp4等视频封装格式解成一个个视音频压缩的数据
    // 打开视频文件，设置文件连接到 fmt_ctx--
    ret = avformat_open_input(&fmt_ctx, video, NULL, NULL);
    if (ret < 0) {
        fprintf(stderr, "Not Open Video File\n");
        return ret;
    }
    //1.1、获取码流信息--把流信息给到 fmt_ctx --给每个媒体流（音频/视频）的AVStream结构体赋值
    ret = avformat_find_stream_info(fmt_ctx, NULL);
    if (ret < 0) {
        fprintf(stderr, "No Found Video File\n");
        return ret;
    }

    //fmt_ctx->streams;
    //可以直接找到视频流的位置通过fmt_ctx
    int i = 0,videoindex = 0;
    for(i=0;i<fmt_ctx->nb_streams;i++){
        if( fmt_ctx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO){
            videoindex = i;
            break;
        }
    }
    printf("vodeoindex2=%d\n",videoindex);
    if( videoindex == -1){
        printf("couldn't find a video stream\n");
        return -1;
    }

    AVCodecParameters *avCodecParameters;
    avCodecParameters = fmt_ctx->streams[videoindex]->codecpar;

    //根据编码器id，查找对应的解码器
    AVCodec *codec = avcodec_find_decoder(avCodecParameters->codec_id);
    if(!codec){
        printf("Cannot find any codec for audio.");
        return -1;
    }
    AVCodecContext *codecCtx = avcodec_alloc_context3(codec);
    if(avcodec_parameters_to_context(codecCtx,avCodecParameters)<0){
        printf("Cannot alloc codec context.");
        return -1;
    }
    codecCtx->pkt_timebase = fmt_ctx->streams[videoindex]->time_base;

    if(avcodec_open2(codecCtx,codec,NULL)<0){
        printf("Cannot open audio codec.");
        return -1;
    }

    /**
     * 新的API主要是以avcodec_send_frame和avcodec_receive_packet代替了之前的avcodec_encode_video2和avcodec_decode_video2。
     * avcodec_send_frame()负责给编码器提供未压缩的原始数据，avcodec_receive_packet()则从编码器取出编码后的数据。
     * 如果返回0，代表成功；返回AGAIN，代表当前状态没有可输出数据；返回EOF，代表已经到达输入流结尾；返回INVAL，代表编码器没有打开或者打开的是解码器。
     */
    AVFrame *frame = av_frame_alloc();

    //循环读取每一帧视频或者音频若干帧压缩数据-或者定位文件avformat_seek_file()+av_seek_frame()
    while (av_read_frame(fmt_ctx, packet) >= 0){//读取一包，存放在AVPacket中
        //todo 2、packet中的数据是视频h.256或音频aac等压缩的视音频数据
        printf("streamIndex=%d, size=%d, pts=%lld, flag=%d\n",
               packet->stream_index,
               packet->size,
               packet->pts,
               packet->flags);

        if (packet->stream_index == 1){
            //视频h.256等压缩格式的-解压--发送解码数据
            printf("video\n");
            decode(codecCtx,packet,frame,outfile);
        }else if (packet->stream_index == 0){
            //音频aac等压缩格式的-解压
            //经过解码之后，获取到的数据是pcm等音频源码数据
            printf("audio\n");
            decodeAudio(codecCtx,packet,frame,outfile);
        }
        // todo 3、解码之后的处理视频帧---源视音频帧数据

        //释放packet包
        av_packet_unref(packet);
    }
    // 关闭视频文件
    avformat_close_input(&fmt_ctx);


    fclose(outfile);
//    fclose(infile);

    av_frame_free(&frame);
    av_packet_free(&packet);
    avcodec_close(codecCtx);
    avcodec_free_context(&codecCtx);
//    avformat_free_context(fmtCtx);
    return 0;
}

/**
 *
 */
