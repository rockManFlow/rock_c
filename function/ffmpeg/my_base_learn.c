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
    AVFormatContext *fmt_ctx = NULL;
    //av视频包数据
    AVPacket *packet= av_packet_alloc();
    int ret;
    // 打开视频文件，设置文件连接到 fmt_ctx
    ret = avformat_open_input(&fmt_ctx, video, NULL, NULL);
    if (ret < 0) {
        fprintf(stderr, "Not Open Video File\n");
        return ret;
    }
    //获取码流信息--把流信息给到 fmt_ctx
    ret = avformat_find_stream_info(fmt_ctx, NULL);
    if (ret < 0) {
        fprintf(stderr, "No Found Video File\n");
        return ret;
    }
    //循环读取每一帧视频或者音频若干帧压缩数据-或者定位文件avformat_seek_file()+av_seek_frame()
    //todo 1、这个类似于解封装：把mp4等视频封装格式解成一个个视音频压缩的数据
    while (av_read_frame(fmt_ctx, packet) >= 0){
        //todo 2、packet中的数据是视频h.256或音频aac等压缩的视音频数据
        if (packet->stream_index == 1){
            //视频h.256等压缩格式的-解压
            ret = avcodec_decode_video2(video_dec_ctx, packet);
            //经过解码之后，获取到的数据是yuv等视频源码数据
        }else if (packet->stream_index == 0){
            //音频aac等压缩格式的-解压
            ret = avcodec_decode_audio4(audio_dec_ctx, packet);
            //经过解码之后，获取到的数据是pcm等音频源码数据
        }
        // todo 3、解码之后的处理视频帧---源视音频帧数据
        printf("streamIndex=%d, size=%d, pts=%lld, flag=%d\n",
               packet->stream_index,
               packet->size,
               packet->pts,
               packet->flags);
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
