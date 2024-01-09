/**
 * 简单的打开-读取-解码-关闭流程
 */
#include "../../include/ffmpeg/win/libavformat/avformat.h"
#include "../../include/ffmpeg/win/libavcodec/packet.h"
#include "../../include/ffmpeg/win/libavcodec/avcodec.h"
#include "../../include/ffmpeg/win/libavcodec/vdpau.h"

int main(){
    //这将注册所有的FFmpeg组件，以便在后续的操作中能够正确地使用它们。
//    av_demuxer_iterate();

    const char* video = "D:\\myProjects\\rock_c\\source\\test.mp4";
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
    //循环读取每一帧视频或者音频若干帧压缩数据-或者定位文件avformat_seek_file()+av_seek_frame()
    while (av_read_frame(fmt_ctx, packet) >= 0){//读取一包，存放在AVPacket中
        AVFrame *frame = av_frame_alloc();
        //todo 2、packet中的数据是视频h.256或音频aac等压缩的视音频数据
        printf("streamIndex=%d, size=%d, pts=%lld, flag=%d\n",
               packet->stream_index,
               packet->size,
               packet->pts,
               packet->flags);

        if (packet->stream_index == 1){
            //视频h.256等压缩格式的-解压--发送解码数据
            ret = avcodec_send_packet(codecCtx, packet);//将AVPacket解码，将解码后的数据存放在AVFrame中
            if( ret < 0){
                printf("avcodec_decode_video2 failed:%d\n", ret);
                return -1;
            }
            //经过解码之后，获取到的数据是yuv等视频源码数据-AVFrame类型)就是最原始的视频数据，我们可以对原始数据进行处理(缩放，裁剪等)
            //接收解码后的帧
            avcodec_receive_frame(codecCtx,frame);
        }else if (packet->stream_index == 0){
            //音频aac等压缩格式的-解压
            ret = avcodec_decode_audio4(audio_dec_ctx, packet);
            //经过解码之后，获取到的数据是pcm等音频源码数据
        }
        // todo 3、解码之后的处理视频帧---源视音频帧数据

        //释放packet包
        av_packet_unref(packet);
    }
    // 关闭视频文件
    avformat_close_input(&fmt_ctx);
    return 0;
}

/**
 *
 */
