//
// Created by Dell on 2024/1/26.
//
#include <stdio.h>
#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#include <libavutil/imgutils.h>
#include <libavutil/opt.h>
#include "libswresample/swresample.h"

#define MAX_AUDIO_FRAME_SIZE 192000
/**
 * ��Ƶ�������--����֤
 *
 * @return
 */
int main() {
    char *input_url="D:\\myProjects\\rock_c\\source\\test.mp3";
    char *out_url="D:\\myProjects\\rock_c\\source\\test_out_audio1.pcm";
    // �������ļ�
    AVFormatContext *formatContext = NULL;
    if (avformat_open_input(&formatContext, input_url, NULL, NULL) != 0) {
        printf("�޷��������ļ�\n");
        return -1;
    }
    // ������Ƶ��
    if (avformat_find_stream_info(formatContext, NULL) < 0) {
        printf("�޷��ҵ���Ƶ��\n");
        return -1;
    }
    // ������Ƶ������
    int videoStreamIndex = -1;
    AVCodecParameters *codecParameters = NULL;
    AVCodec *codec = NULL;
    for (int i = 0; i < formatContext->nb_streams; i++) {
        if (formatContext->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_AUDIO) {
            videoStreamIndex = i;
            codecParameters = formatContext->streams[i]->codecpar;
            //���ҽ�����
            codec = avcodec_find_decoder(codecParameters->codec_id);
            break;
        }
    }
    if (videoStreamIndex == -1 || codecParameters == NULL || codec == NULL) {
        printf("�޷��ҵ���Ƶ������\n");
        return -1;
    }
    // ��������������
    AVCodecContext *codecContext = avcodec_alloc_context3(codec);
    if(avcodec_parameters_to_context(codecContext,codecParameters)<0){
        printf("Cannot alloc codec context.");
        return -1;
    }

    //����Ƶ������
    if (avcodec_open2(codecContext, codec, NULL) < 0) {
        printf("�޷�����Ƶ������\n");
        return -1;
    }

    //��Ƶ�������
    uint64_t out_channel_layout = AV_CH_LAYOUT_STEREO;//������ʽ
    enum AVSampleFormat out_sample_fmt = AV_SAMPLE_FMT_S16;//������ʽ
    int out_nb_samples = codecContext->frame_size;//nb_samples: AAC-1024 MP3-1152
    int out_sample_rate = 48000;//������
    int out_nb_channels = av_get_channel_layout_nb_channels(out_channel_layout);//����������ʽ������������
    int out_buffer_size = av_samples_get_buffer_size(NULL, out_nb_channels, out_nb_samples, out_sample_fmt, 1);

    uint8_t *buffer = (uint8_t *)av_malloc(MAX_AUDIO_FRAME_SIZE);

    SwrContext *audio_convert_ctx = swr_alloc();
    if (audio_convert_ctx == NULL)
    {
        printf("Could not allocate SwrContext\n");
        return -1;
    }

    swr_alloc_set_opts(audio_convert_ctx, out_channel_layout, out_sample_fmt,out_sample_rate,
                       codecContext->channel_layout, codecContext->sample_fmt, codecContext->sample_rate, 0, NULL);
    swr_init(audio_convert_ctx);

    // ����֡�������ڱ��������ͼ��
    AVFrame *frame = av_frame_alloc();

    // ��ת�����ͼ�����ݱ��浽�ļ�
    FILE *outputFile = fopen(out_url, "wb+");
    // ��ȡ��Ƶ֡�����н���͸�ʽת��
    AVPacket *packet=av_packet_alloc();
    while (av_read_frame(formatContext, packet) >= 0) {
        if (packet->stream_index == videoStreamIndex) {
            // ������Ƶ֡
            avcodec_send_packet(codecContext, packet);
            while(avcodec_receive_frame(codecContext, frame)>=0){
                swr_convert(audio_convert_ctx, &buffer, MAX_AUDIO_FRAME_SIZE, (const uint8_t **)frame->data, frame->nb_samples);
                fwrite(buffer, 1, out_buffer_size, outputFile);
                // ��ʽת��
//                int data_size = av_get_bytes_per_sample(codecContext->sample_fmt);
//                int i, ch;
//                for (i = 0; i < frame->nb_samples; i++) {
//                    for (ch = 0; ch < codecContext->channels;ch++)  // ����ķ�ʽд��, �󲿷�float�ĸ�ʽ���
//                        fwrite(frame->data[ch] + data_size * i, 1, data_size, outputFile);
//                }
            }
        }
        av_packet_unref(packet);
    }
    // �ͷ���Դ
    fclose(outputFile);
    av_frame_free(&frame);
    av_free(buffer);
    avcodec_free_context(&codecContext);
    avformat_close_input(&formatContext);
    av_packet_free(&packet);
    swr_free(&audio_convert_ctx);
    return 0;
}
