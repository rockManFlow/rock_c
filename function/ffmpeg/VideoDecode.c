#include <stdio.h>
#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#include <libavutil/imgutils.h>
#include <libavutil/opt.h>
#include "../../include/ffmpeg/win/libswscale/swscale.h"

/**
 * 经验证，是好用的，可以把封装的文件转成YUV等指定解码后的数据
 * 并使用ffplay进行播放也是正常的
 * -video_size大小需要特别注意下，在程序中会打印对应大小
 * ffplay -video_size 368x640 -pix_fmt yuv420p test_out_video.yuv
 *
 * YUV+RGB 视频的解码
 * 都没问题
 * mp4-yuv
 * mkv-yuv
 * flv-yuv
 * @return
 */
int main() {
    char *input_url="D:\\myProjects\\rock_c\\source\\test2.mkv";
    char *out_url="D:\\myProjects\\rock_c\\source\\test_out_video_2.yuv";
//    char *out_url="D:\\myProjects\\rock_c\\source\\test_out_video_01.rgb";
    // 打开输入文件
    AVFormatContext *formatContext = NULL;
    if (avformat_open_input(&formatContext, input_url, NULL, NULL) != 0) {
        printf("无法打开输入文件\n");
        return -1;
    }
    // 查找视频流
    if (avformat_find_stream_info(formatContext, NULL) < 0) {
        printf("无法找到视频流\n");
        return -1;
    }
    // 查找视频解码器
    int videoStreamIndex = -1;
    AVCodecParameters *codecParameters = NULL;
    AVCodec *codec = NULL;
    for (int i = 0; i < formatContext->nb_streams; i++) {
        if (formatContext->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
            videoStreamIndex = i;
            codecParameters = formatContext->streams[i]->codecpar;
            //查找解码器
            codec = avcodec_find_decoder(codecParameters->codec_id);
            break;
        }
    }
    if (videoStreamIndex == -1 || codecParameters == NULL || codec == NULL) {
        printf("无法找到视频解码器\n");
        return -1;
    }
    // 分配编解码上下文
    AVCodecContext *codecContext = avcodec_alloc_context3(codec);
    if(avcodec_parameters_to_context(codecContext,codecParameters)<0){
        printf("Cannot alloc codec context.");
        return -1;
    }

    //打开视频解码器
    if (avcodec_open2(codecContext, codec, NULL) < 0) {
        printf("无法打开视频解码器\n");
        return -1;
    }
    // 创建帧对象用于保存解码后的图像
    AVFrame *frame = av_frame_alloc();
    AVFrame *outputFrame = av_frame_alloc();
    // 设置解码后图像的格式为YUV420--转换后的格式
    enum AVPixelFormat outputFormat =AV_PIX_FMT_YUV420P;// AV_PIX_FMT_YUV420P
    int outputWidth = codecContext->width;
    int outputHeight = codecContext->height;
    printf("video size outputWidth:%d\n",outputWidth);
    printf("video size outputHeight:%d\n",outputHeight);
    // 分配内存用于保存解码后的图像数据
    int bufferSize = av_image_get_buffer_size(outputFormat, outputWidth, outputHeight, 1);
    uint8_t *buffer = (uint8_t *) av_malloc(bufferSize);
    //瓜分内存
    av_image_fill_arrays(outputFrame->data, outputFrame->linesize, buffer, outputFormat, outputWidth,outputHeight, 1);

    // 创建用于图像转换的上下文
    struct SwsContext *swsContext = sws_getContext(codecContext->width, codecContext->height,codecContext->pix_fmt, codecContext->width, codecContext->height,
                                                   outputFormat, SWS_BICUBIC, NULL, NULL, NULL);

    // 将转换后的图像数据保存到文件
    FILE *outputFile = fopen(out_url, "wb+");
    // 读取视频帧并进行解码和格式转换
    AVPacket *packet=av_packet_alloc();
    while (av_read_frame(formatContext, packet) >= 0) {
        if (packet->stream_index == videoStreamIndex) {
            // 解码视频帧
            avcodec_send_packet(codecContext, packet);
            while(avcodec_receive_frame(codecContext, frame)>=0){
                // 格式转换
                sws_scale(swsContext, (const uint8_t* const*)frame->data, frame->linesize, 0, codecContext->height,outputFrame->data, outputFrame->linesize);

                //方式1--保存YUV420P格式的数据
                int y_size = codecContext->width * codecContext->height;
                //Y亮度信息
                fwrite(outputFrame->data[0], 1, y_size, outputFile);
                //色度
                fwrite(outputFrame->data[1], 1, y_size / 4, outputFile);
                //浓度
                fwrite(outputFrame->data[2], 1, y_size / 4, outputFile);
                //方式2--保存YUV420P格式的数据
//                for (int i = 0; i < outputFrame->height; i++) {
//                    fwrite(outputFrame->data[0] + i * outputFrame->linesize[0], 1, outputFrame->width, outputFile);
//                }
//                for (int i = 0; i < outputFrame->height / 2; i++) {
//                    fwrite(outputFrame->data[1] + i * outputFrame->linesize[1], 1, outputFrame->width / 2, outputFile);
//                }
//                for (int i = 0; i < outputFrame->height / 2; i++) {
//                    fwrite(outputFrame->data[2] + i * outputFrame->linesize[2], 1, outputFrame->width / 2, outputFile);
//                }
            }

            //保存RGB24格式的数据
//            fwrite(outputFrame->data[0],(codecContext->width)*(codecContext->height)*3,1,outputFile);

            //保存UYVY格式的数据
//            fwrite(outputFrame->data[0],(codecContext->width)*(codecContext->height),2,outputFile);
        }
        av_packet_unref(packet);
    }
    // 释放资源
    fclose(outputFile);
    av_frame_free(&frame);
    av_frame_free(&outputFrame);
    avcodec_free_context(&codecContext);
    avformat_close_input(&formatContext);
    av_free(buffer);
    av_packet_free(&packet);
    return 0;
}