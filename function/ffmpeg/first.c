/**
 * todo 视频解码？？
 */
#include<stdio.h>
#include "../../include/ffmpeg/libavcodec/avcodec.h"
#include "../../include/ffmpeg/libavformat/avformat.h"
#include "../../include/ffmpeg/libavformat/avio.h"
#include "../../include/ffmpeg/libavutil/log.h"
#include "../../include/ffmpeg/libavutil/timestamp.h"
#define ERROR_STR_SIZE 1024
int main(int argc, char const *argv[]) {
    int ret = -1;
    int err_code;
    char errors[ERROR_STR_SIZE];
    AVFormatContext *ifmt_ctx1 = NULL;
    AVFormatContext *ifmt_ctx2 = NULL;
    AVFormatContext *ofmt_ctx = NULL;
    AVOutputFormat *ofmt = NULL;
    AVStream *in_stream1 = NULL;
    AVStream *in_stream2 = NULL;
    AVStream *out_stream1 = NULL;
    AVStream *out_stream2 = NULL;
    int audio_stream_index = 0;
    int vedio_stream_indes = 0;
    // 文件最大时长，保证音频和视频数据长度一致
    double max_duration = 0;
    AVPacket pkt;
    int stream1 = 0, stream2 = 0;
    av_log_set_level(AV_LOG_DEBUG);
    //打开两个输入文件
    if ((err_code = avformat_open_input(&ifmt_ctx1, "C:\\Users\\haizhengzheng\\Desktop\\meta.mp4", 0, 0)) < 0) {
        av_strerror(err_code, errors, ERROR_STR_SIZE);
        av_log(NULL, AV_LOG_ERROR, "Could not open src file, %s, %d(%s)\n",
               "C:\\Users\\haizhengzheng\\Desktop\\meta.mp4", err_code, errors);
        goto END;
    }
    if ((err_code = avformat_open_input(&ifmt_ctx2, "C:\\Users\\haizhengzheng\\Desktop\\mercury.mp4", 0, 0)) < 0) {
        av_strerror(err_code, errors, ERROR_STR_SIZE);
        av_log(NULL, AV_LOG_ERROR,
               "Could not open the second src file, %s, %d(%s)\n",
               "C:\\Users\\haizhengzheng\\Desktop\\mercury.mp4", err_code, errors);
        goto END;
    }
    //创建输出上下文
    if ((err_code = avformat_alloc_output_context2(&ofmt_ctx, NULL, NULL,
                                                   "C:\\Users\\haizhengzheng\\Desktop\\amv.mp4")) < 0) {
        av_strerror(err_code, errors, ERROR_STR_SIZE);
        av_log(NULL, AV_LOG_ERROR, "Failed to create an context of outfile , %d(%s) \n",
               err_code, errors);
    }
    ofmt = ofmt_ctx->oformat;//获得输出文件的格式信息
    // 找到第一个参数里最好的音频流和第二个文件中的视频流下标
    audio_stream_index = av_find_best_stream(ifmt_ctx1, AVMEDIA_TYPE_AUDIO, -1, -1, NULL, 0);//获取音频流下标
    vedio_stream_indes = av_find_best_stream(ifmt_ctx2, AVMEDIA_TYPE_VIDEO, -1, -1, NULL, 0);//获取视频流下标
    // 获取第一个文件中的音频流
    in_stream1 = ifmt_ctx1->streams[audio_stream_index];
    stream1 = 0;
    // 创建音频输出流
    out_stream1 = avformat_new_stream(ofmt_ctx, NULL);
    if (!out_stream1) {
        av_log(NULL, AV_LOG_ERROR, "Failed to alloc out stream!\n");
        goto END;
    }
    // 拷贝流参数
    if ((err_code = avcodec_parameters_copy(out_stream1->codecpar, in_stream1->codecpar)) < 0) {
        av_strerror(err_code, errors, ERROR_STR_SIZE);
        av_log(NULL, AV_LOG_ERROR,
               "Failed to copy codec parameter, %d(%s)\n",
               err_code, errors);
    }
    out_stream1->codecpar->codec_tag = 0;
    // 获取第二个文件中的视频流
    in_stream2 = ifmt_ctx2->streams[vedio_stream_indes];
    stream2 = 1;

    // 创建视频输出流
    out_stream2 = avformat_new_stream(ofmt_ctx, NULL);
    if (!out_stream2) {
        av_log(NULL, AV_LOG_ERROR, "Failed to alloc out stream!\n");
        goto END;
    }
    // 拷贝流参数
    if ((err_code = avcodec_parameters_copy(out_stream2->codecpar, in_stream2->codecpar)) < 0) {
        av_strerror(err_code, errors, ERROR_STR_SIZE);
        av_log(NULL, AV_LOG_ERROR,
               "Failed to copy codec parameter, %d(%s)\n",
               err_code, errors);
        goto END;
    }
    out_stream2->codecpar->codec_tag = 0;
    //输出流信息
    av_dump_format(ofmt_ctx, 0, "C:\\Users\\haizhengzheng\\Desktop\\amv.mp4", 1);

    // 判断两个流的长度，确定最终文件的长度    time(秒) = st->duration * av_q2d(st->time_base)   duration 就是dts\pts     av_q2d()就是倒数
    if (in_stream1->duration * av_q2d(in_stream1->time_base) > in_stream2->duration * av_q2d(in_stream2->time_base)) {
        max_duration = in_stream2->duration * av_q2d(in_stream2->time_base);
    } else {
        max_duration = in_stream1->duration * av_q2d(in_stream1->time_base);
    }
    //打开输出文件
    if (!(ofmt->flags & AVFMT_NOFILE)) {
        if ((err_code = avio_open(&ofmt_ctx->pb, "C:\\Users\\haizhengzheng\\Desktop\\amv.mp4", AVIO_FLAG_WRITE)) < 0) {
            av_strerror(err_code, errors, ERROR_STR_SIZE);
            av_log(NULL, AV_LOG_ERROR,
                   "Could not open output file, %s, %d(%s)\n",
                   "C:\\Users\\haizhengzheng\\Desktop\\amv.mp4", err_code, errors);
            goto END;
        }
    }
    //写头信息
    avformat_write_header(ofmt_ctx, NULL);
    av_init_packet(&pkt);
    // 读取音频数据并写入输出文件中
    while (av_read_frame(ifmt_ctx1, &pkt) >= 0) {
        // 如果读取的时间超过了最长时间表示不需要该帧，跳过
        if (pkt.pts * av_q2d(in_stream1->time_base) > max_duration) {
            av_packet_unref(&pkt);
            continue;
        }
        // 如果是我们需要的音频流，转换时间基后写入文件  av_rescale_q_rnd()时间基转换函数
        if (pkt.stream_index == audio_stream_index) {
            pkt.pts = av_rescale_q_rnd(pkt.pts, in_stream1->time_base, out_stream1->time_base,//获取包的PTS\DTS\duration
                                       (AV_ROUND_NEAR_INF | AV_ROUND_PASS_MINMAX));
            pkt.dts = av_rescale_q_rnd(pkt.dts, in_stream1->time_base, out_stream1->time_base,
                                       (AV_ROUND_NEAR_INF | AV_ROUND_PASS_MINMAX));
            pkt.duration = av_rescale_q(max_duration, in_stream1->time_base, out_stream1->time_base);
            pkt.pos = -1;
            pkt.stream_index = stream1;
            av_interleaved_write_frame(ofmt_ctx, &pkt);
            av_packet_unref(&pkt);
        }
    }
    // 读取视频数据并写入输出文件中
    while (av_read_frame(ifmt_ctx2, &pkt) >= 0) {

        // 如果读取的时间超过了最长时间表示不需要该帧，跳过
        if (pkt.pts * av_q2d(in_stream2->time_base) > max_duration) {
            av_packet_unref(&pkt);
            continue;
        }
        // 如果是我们需要的视频流，转换时间基后写入文件
        if (pkt.stream_index == vedio_stream_indes) {
            pkt.pts = av_rescale_q_rnd(pkt.pts, in_stream2->time_base, out_stream2->time_base,
                                       (AV_ROUND_NEAR_INF | AV_ROUND_PASS_MINMAX));
            pkt.dts = av_rescale_q_rnd(pkt.dts, in_stream2->time_base, out_stream2->time_base,
                                       (AV_ROUND_NEAR_INF | AV_ROUND_PASS_MINMAX));
            pkt.duration = av_rescale_q(max_duration, in_stream2->time_base, out_stream2->time_base);
            pkt.pos = -1;
            pkt.stream_index = stream2;
            av_interleaved_write_frame(ofmt_ctx, &pkt);
            av_packet_unref(&pkt);//必须要有，否则每帧解码av_read_frame都会产生堆内存，造成内存泄漏
        }
    }
    //写尾信息
    av_write_trailer(ofmt_ctx);
    ret = 0;
    END:
    // 释放内存
    if (ifmt_ctx1) {
        avformat_close_input(&ifmt_ctx1);
    }

    if (ifmt_ctx2) {
        avformat_close_input(&ifmt_ctx2);
    }

    if (ofmt_ctx) {
        if (!(ofmt->flags & AVFMT_NOFILE)) {
            avio_closep(&ofmt_ctx->pb);
        }
        avformat_free_context(ofmt_ctx);
    }
}