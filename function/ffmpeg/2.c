//
// Created by opayc on 2023/11/27.
//
#include <stdio.h>
#include "../../include/ffmpeg/libavcodec/avcodec.h"
#include "../../include/ffmpeg/libavformat/avformat.h"

#define ERROR_STR_SIZE 1024
/**
 * todo 待测试？？
 * @return
 */
int main() {

    int ret = -1;
    int err_code;
    char errors[ERROR_STR_SIZE];
    AVFormatContext *ifmt_ctx = NULL;
    AVCodecContext *pCodecCtx;
    const AVOutputFormat *ofmt = NULL;
    AVStream *in_stream1 = NULL;
    int vedio_stream_indes = 0;
    // 文件最大时长，保证音频和视频数据长度一致
    double max_duration = 0;
    AVPacket *pkt;
    int stream1 = 0, stream2 = 0;
    const char* video = "/Users/opayc/products/rock_c/source/test.mp4";
    /*//初始化网络，如果要解封装网络数据格式，则可调用该函数。
    avformat_network_init();*/
    //av_register_all();//高版本已弃用
    //打开输入文件(可解析视频参数)
    ifmt_ctx = avformat_alloc_context();
    if ((err_code = avformat_open_input(&ifmt_ctx, video, 0, 0)) != 0) {
        av_strerror(err_code, errors, ERROR_STR_SIZE);
        return -1;
    }
    if (avformat_find_stream_info(ifmt_ctx, NULL) < 0) {
        printf("Couldn't find stream information.\n");
        return -1;
    }

    // 找到视频流下标
    vedio_stream_indes = -1;
    for (int i = 0; i < ifmt_ctx->nb_streams; i++) {
        if (ifmt_ctx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
            vedio_stream_indes = i;
            break;
        }
    }

    if (vedio_stream_indes == -1) {
        printf("Didn't find a video stream");
        return -1;
    }
    // 获取文件中的视频流
    in_stream1 = ifmt_ctx->streams[vedio_stream_indes];
    int rate = in_stream1->r_frame_rate.num;   //视频帧率
    int w = in_stream1->codecpar->width;   //视频图像宽
    int h = in_stream1->codecpar->height;   //视频图像高
    if (in_stream1->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
        printf("the input is video.\n");
    }
    //软解码，查找解码器
    const AVCodec *pCodec = avcodec_find_decoder(in_stream1->codecpar->codec_id);
    if (pCodec == NULL) {
        printf("Codec not found.\n");
        return -1;
    }

    //获取编解码器上下文
    pCodecCtx = avcodec_alloc_context3(pCodec);
    if (pCodecCtx == NULL)
    {
        printf("get av codec context failed.\n");
        return -1;
    }
    //设置编解码器上下文参数
    if (avcodec_parameters_to_context(pCodecCtx, in_stream1->codecpar) < 0) {
        printf("set param failed!\n");
        return -1;
    }

    //打开编解码器
    if (avcodec_open2(pCodecCtx, pCodec, NULL) != 0) {
        printf("Could not open codec.\n");
        return -1;
    }
    AVFrame *frame;
    //frame 初始化
    frame = av_frame_alloc();
    pkt = av_packet_alloc();
    int frames = 0;
    while (av_read_frame(ifmt_ctx, pkt)>=0) {

        if (pkt->stream_index == vedio_stream_indes) {
            //解封装，将包发送到解码队列中
            ret = avcodec_send_packet(pCodecCtx, pkt);

            if (ret < 0) {
                continue;
            }
            if (ret >= 0) {

                ret = avcodec_receive_frame(pCodecCtx, frame);
                int keyfrmae = frame->key_frame;  //是否为I帧
                printf("keyframe:%d\n", keyfrmae);
                if (ret == 0) {
                    frames += 1;
                    //printf("decodec sucess.\n");
                    printf("frames:%d\n",frames);
                } else {
                    printf("receive failed.\n");
                }
            }
        }
        av_packet_unref(pkt);    //必须要有，否则每帧解码av_read_frame都会产生堆内存，造成内存泄漏
    }
    av_free(frame->data);
    avcodec_free_context(&pCodecCtx);
    avformat_free_context(ifmt_ctx);
    av_packet_free(&pkt);
    return 1;

}