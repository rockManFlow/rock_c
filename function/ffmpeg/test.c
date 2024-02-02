/**
 *
 *������ʵ������ƵPCM�������ݱ���Ϊѹ��������MP3��WMA��AAC�ȣ���
 * ����֤
 *
 */
#include <stdio.h>

#define __STDC_CONSTANT_MACROS


#include "libavformat/avformat.h"
#include "libavformat/avio.h"
#include "libavdevice/avdevice.h"
#include "libavcodec/avcodec.h"
#include "libswscale/swscale.h"
#include "libswresample/swresample.h"
#include "libavutil/imgutils.h"



int flush_encoder(AVCodecContext *pCodecCtx, FILE* jpeg_file,AVFrame *pFrame)
{
    int ret;
    AVPacket enc_pkt;
    while (1)
    {
        enc_pkt.data = NULL;
        enc_pkt.size = 0;
        av_init_packet(&enc_pkt);
//
//        ret = avcodec_encode_audio2(fmt_ctx->streams[stream_index]->codec, &enc_pkt,
//                                    NULL, &got_frame);
        //Encode ����
        int ret =avcodec_send_frame(pCodecCtx,pFrame);
        if(ret < 0){
            printf("Encode Error ret=%d.\n",ret);
            return -1;
        }

        ret=avcodec_receive_packet(pCodecCtx,&enc_pkt);
        av_frame_free(NULL);
        if (ret < 0)
            break;
        printf("Flush Encoder: Succeed to encode 1 frame!\tsize:%5d\n", enc_pkt.size);
        fwrite(enc_pkt.data, 1, enc_pkt.size, jpeg_file);
        /* mux encoded frame */
//        ret = av_write_frame(pFormatCtx, &enc_pkt);
        av_packet_unref(&enc_pkt);
        if (ret < 0)
            break;
    }
    return ret;
}

int main(int argc, char *argv[])
{
    AVFormatContext *pFormatCtx = NULL;
    AVCodecContext *pCodecCtx = NULL;
    AVCodec *pCodec = NULL;
    AVFrame *pFrame = NULL;
    AVPacket pkt;
    int i = 0;

    const char *inFilename = "D:\\myProjects\\rock_c\\source\\NocturneNo2inEflat_44.1k_s16le.pcm";
    const char *outFilename = "D:\\myProjects\\rock_c\\source\\NocturneNo2inEflat_44.1k_s16le_1.aac";

    avdevice_register_all();

    avformat_alloc_output_context2(&pFormatCtx, NULL, NULL, outFilename);

    if (avio_open(&pFormatCtx->pb, outFilename, AVIO_FLAG_READ_WRITE) < 0)
    {
        printf("can't open output file\n");
        return -1;
    }

    AVStream *stream = avformat_new_stream(pFormatCtx, NULL);
    if (!stream)
    {
        printf("can't allocate new stream\n");
        return -1;
    }

    //���ò���
    AVCodec* avCodec = avcodec_find_encoder(pFormatCtx->oformat->audio_codec);
    if (!avCodec){
        printf("Can not find encoder!\n");
        return -1;
    }

    pCodecCtx = avcodec_alloc_context3(avCodec);
    pCodecCtx->codec_id = pFormatCtx->oformat->audio_codec;
    pCodecCtx->codec_type = AVMEDIA_TYPE_AUDIO;
    pCodecCtx->sample_fmt = AV_SAMPLE_FMT_FLTP;
    pCodecCtx->sample_rate = 44100;
    pCodecCtx->channel_layout = AV_CH_LAYOUT_STEREO;
    pCodecCtx->channels = av_get_channel_layout_nb_channels(pCodecCtx->channel_layout);
    pCodecCtx->bit_rate = 128000;
//	pCodecCtx->frame_size = 1024;

//���ұ�����
    pCodec = avcodec_find_encoder(pCodecCtx->codec_id);
    if (!pCodec)
    {
        printf("can't find encoder\n");
        return -1;
    }

    //�򿪱�����
    if (avcodec_open2(pCodecCtx, pCodec, NULL) < 0)
    {
        printf("can't open encoder\n");
        return -1;
    }

    pFrame = av_frame_alloc();
    if (!pFrame)
    {
        printf("can't alloc frame\n");
        return -1;
    }

    pFrame->nb_samples = pCodecCtx->frame_size;
    pFrame->format = pCodecCtx->sample_fmt;
    pFrame->channels = 2;

    //PCM�ز���
    SwrContext *swrCtx = swr_alloc();

    swr_alloc_set_opts(swrCtx, av_get_default_channel_layout(pCodecCtx->channels), pCodecCtx->sample_fmt,
                       pCodecCtx->sample_rate, av_get_default_channel_layout(pFrame->channels), AV_SAMPLE_FMT_S16, //PCMԴ�ļ��Ĳ�����ʽ
                       44100, 0, NULL);
    swr_init(swrCtx);

    /* ����ռ� */
    uint8_t **convert_data = (uint8_t**) calloc(pCodecCtx->channels, sizeof(*convert_data));
    av_samples_alloc(convert_data, NULL, pCodecCtx->channels, pCodecCtx->frame_size, pCodecCtx->sample_fmt, 0);


    int size = av_samples_get_buffer_size(NULL, pCodecCtx->channels, pCodecCtx->frame_size, pCodecCtx->sample_fmt, 1);
    uint8_t *frameBuf = (uint8_t*) av_malloc(size);
    avcodec_fill_audio_frame(pFrame, pCodecCtx->channels, pCodecCtx->sample_fmt, (const uint8_t*) frameBuf, size, 1);

    //д֡ͷ
    avformat_write_header(pFormatCtx, NULL);

    FILE *inFile = fopen(inFilename, "rb");

    av_init_packet(&pkt);
    pkt.data = NULL;
    pkt.size = 0;

    FILE* jpeg_file = fopen(outFilename, "wb");
    for (i = 0;; i++)
    {
        //����һ֡���ݵĳ���
        int length = pFrame->nb_samples * av_get_bytes_per_sample(AV_SAMPLE_FMT_S16) * pFrame->channels;

        //��PCM������ע���ȡ�ĳ��ȣ�������ܳ���ת��֮�����������߱���
        if (fread(frameBuf, 1, length, inFile) <= 0)
        {
            printf("failed to read raw data\n");
            return -1;
        }
        else if (feof(inFile))
        {
            break;
        }

        swr_convert(swrCtx, convert_data, pCodecCtx->frame_size, (const uint8_t**) pFrame->data, pFrame->nb_samples);

        //���һ֡���ݵĳ���
        length = pCodecCtx->frame_size * av_get_bytes_per_sample(pCodecCtx->sample_fmt);
        //˫ͨ����ֵ�������AACΪ˫ͨ����
        memcpy(pFrame->data[0], convert_data[0], length);
        memcpy(pFrame->data[1], convert_data[1], length);

        pFrame->pts = i * 100;

        if (avcodec_send_frame(pCodecCtx, pFrame) < 0)
        {
            printf("can't send frame for encoding\n");
            break;
        }

        if (avcodec_receive_packet(pCodecCtx, &pkt) >= 0)
        {
            pkt.stream_index = stream->index;
            printf("write %4d frame, size = %d, length = %d\n", i, size, length);
            fwrite(pkt.data, 1, pkt.size, jpeg_file);
//            av_write_frame(pFormatCtx, &pkt);
        }

        av_packet_unref(&pkt);
    }

    //flush encoder
    if (flush_encoder(pCodecCtx,jpeg_file, pFrame) < 0)
    {
        printf("flushing encoder failed\n");
        return -1;
    }

    //write trailer
    av_write_trailer(pFormatCtx);

    avcodec_close(pCodec);
    av_free(pFrame);
    av_free(frameBuf);

    avio_close(pFormatCtx->pb);
    avformat_free_context(pFormatCtx);

    fclose(inFile);

    return 0;
}