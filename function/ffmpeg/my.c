/**
 * 简单的打开-读取-解码-关闭流程
 */
#include "../../include/ffmpeg/libavformat/avformat.h"
#include "../../include/ffmpeg/libavcodec/avcodec.h"

int main(){
    //这将注册所有的FFmpeg组件，以便在后续的操作中能够正确地使用它们。
//    av_demuxer_iterate();
    //av格式上下文
    AVFormatContext *fmt_ctx = NULL;
    //av视频包数据
    AVPacket packet;
    int ret;
    // 打开视频文件
    ret = avformat_open_input(&fmt_ctx, "video.mp4", NULL, NULL);
    if (ret < 0) {
        fprintf(stderr, "无法打开视频文件\n");
        return ret;
    }
    // 解码视频流
    ret = avformat_find_stream_info(fmt_ctx, NULL);
    if (ret < 0) {
        fprintf(stderr, "无法找到视频流\n");
        return ret;
    }
    // 循环解码每一帧视频
    while (av_read_frame(fmt_ctx, &packet) >= 0){
        // 处理视频帧
        packet->
    }
    // 关闭视频文件
    avformat_close_input(&fmt_ctx);
    return 0;
}
