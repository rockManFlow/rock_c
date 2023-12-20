/**
 * 简单的打开-读取-解码-关闭流程
 */
#include "../../include/ffmpeg/win/libavformat/avformat.h"
#include "../../include/ffmpeg/win/libavcodec/packet.h"

int main(){
    //这将注册所有的FFmpeg组件，以便在后续的操作中能够正确地使用它们。
//    av_demuxer_iterate();

    const char* video = "D:\\myProjects\\rock_c\\source\\test.mp4";
    //av格式上下文
    AVFormatContext *fmt_ctx = NULL;
    //av视频包数据
    AVPacket *packet= av_packet_alloc();;
    int ret;
    // 打开视频文件
    ret = avformat_open_input(&fmt_ctx, video, NULL, NULL);
    if (ret < 0) {
        fprintf(stderr, "Not Open Video File\n");
        return ret;
    }
    // 解码视频流--获取码流信息
    ret = avformat_find_stream_info(fmt_ctx, NULL);
    if (ret < 0) {
        fprintf(stderr, "No Found Video File\n");
        return ret;
    }
    // 循环解码读取每一帧视频-或者定位文件avformat_seek_file()+av_seek_frame()
    while (av_read_frame(fmt_ctx, packet) >= 0){
        // 处理视频帧
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
