//
// Created by Dell on 2024/2/1.
//

#include <stdio.h>
#define __STDC_CONSTANT_MACROS
#ifdef _WIN32
//Windows
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#else
//Linux...
#ifdef __cplusplus
extern "C"
{
#endif
#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#ifdef __cplusplus
};
#endif
#endif

/**
 * 待验证
 * @return
 */
int main()
{
    int framenum=1000;
    const char* out_file = "D:\\myProjects\\rock_c\\source\\test_out_audio_1.aac";          //Output URL
    int i;

    FILE *in_file= fopen("D:\\myProjects\\rock_c\\source\\test_out_audio.pcm", "rb");

    //Method 1.
//    AVFormatContext* pFormatCtx = avformat_alloc_context();
//    fmt = av_guess_format(NULL, out_file, NULL);
//    pFormatCtx->oformat = fmt;


    //Method 2.
    AVFormatContext* pFormatCtx=NULL;
    avformat_alloc_output_context2(&pFormatCtx, NULL, NULL, out_file);
    AVOutputFormat* fmt = pFormatCtx->oformat;

    //Open output URL
    if (avio_open(&pFormatCtx->pb,out_file, AVIO_FLAG_READ_WRITE) < 0){
        printf("Failed to open output file!\n");
        return -1;
    }

    AVCodec* avCodec = avcodec_find_encoder(pFormatCtx->oformat->audio_codec);
    if (!avCodec){
        printf("Can not find encoder!\n");
        return -1;
    }

    AVCodecContext* pCodecCtx = avcodec_alloc_context3(avCodec);
    pCodecCtx->codec_id = fmt->audio_codec;
    pCodecCtx->codec_type = AVMEDIA_TYPE_AUDIO;
    //指的是样本的格式。一个音频的样本一般用两个字节来描述，分为大小端。我们的程序中使用的是16bit的小端格式
    pCodecCtx->sample_fmt = AV_SAMPLE_FMT_FLTP;
    //sample_rate指的是采样率。也就是我们一秒钟采集多少次声音样本。
    pCodecCtx->sample_rate= 44100;
    //用来设置输出通道布局
    pCodecCtx->channel_layout=AV_CH_LAYOUT_STEREO;
    //通道数：音频一般有双通道或者单通道之分，一般都是双通道
    pCodecCtx->channels = av_get_channel_layout_nb_channels(pCodecCtx->channel_layout);//声道
    pCodecCtx->bit_rate = 64000;

    if (avcodec_open2(pCodecCtx, avCodec,NULL) < 0){
        printf("Failed to open encoder!\n");
        return -1;
    }
    AVFrame* pFrame = av_frame_alloc();
    pFrame->nb_samples= pCodecCtx->frame_size;
    pFrame->format= pCodecCtx->sample_fmt;
    pFrame->channels = 2;

    //PCM重采样
    SwrContext *swrCtx = swr_alloc();

    swr_alloc_set_opts(swrCtx, av_get_default_channel_layout(pCodecCtx->channels), pCodecCtx->sample_fmt,
                       pCodecCtx->sample_rate, av_get_default_channel_layout(pFrame->channels), AV_SAMPLE_FMT_S16, //PCM源文件的采样格式
                       44100, 0, NULL);
    swr_init(swrCtx);

    uint8_t **convert_data = (uint8_t**) calloc(pCodecCtx->channels, sizeof(*convert_data));
    av_samples_alloc(convert_data, NULL, pCodecCtx->channels, pCodecCtx->frame_size, pCodecCtx->sample_fmt, 0);


    int size = av_samples_get_buffer_size(NULL, pCodecCtx->channels,pCodecCtx->frame_size,pCodecCtx->sample_fmt, 1);
    uint8_t* frame_buf = (uint8_t *)av_malloc(size);
    avcodec_fill_audio_frame(pFrame, pCodecCtx->channels, pCodecCtx->sample_fmt,(const uint8_t*)frame_buf, size, 1);

    //Write Header
    avformat_write_header(pFormatCtx,NULL);
    AVPacket pkt;
    av_new_packet(&pkt,size);


    //方式1
    for (i = 0;; i++)
    {
        //输入一帧数据的长度
        int length = pFrame->nb_samples * av_get_bytes_per_sample(AV_SAMPLE_FMT_S16) * pFrame->channels;

        //读PCM：特意注意读取的长度，否则可能出现转码之后声音变快或者变慢
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

        //输出一帧数据的长度
        length = pCodecCtx->frame_size * av_get_bytes_per_sample(pCodecCtx->sample_fmt);
        //双通道赋值（输出的AAC为双通道）
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
            av_write_frame(pFormatCtx, &pkt);
        }

        av_packet_unref(&pkt);
    }


    //方式2
//    FILE* jpeg_file = fopen(out_file, "wb");
//    for (i=0; i<framenum; i++){
//        //Read PCM
//        if (fread(frame_buf, 1, size, in_file) <= 0){
//            printf("Failed to read raw data! \n");
//            return -1;
//        }else if(feof(in_file)){
//            break;
//        }
//        pFrame->data[0] = frame_buf;  //PCM Data
//
//        pFrame->pts=i*100;
//        //Encode 编码
//        int ret =avcodec_send_frame(pCodecCtx,pFrame);
//        if(ret < 0){
//            printf("Encode Error ret=%d.\n",ret);
//            return -1;
//        }
//
//        ret=avcodec_receive_packet(pCodecCtx,&pkt);
//        if(ret == 0){
//            fwrite(pkt.data, 1, pkt.size, jpeg_file);
//        }
//        av_packet_unref(&pkt);
//    }

    //Write Trailer
    av_write_trailer(pFormatCtx);

    //Clean
    avcodec_close(avCodec);
    av_free(pFrame);
    av_free(frame_buf);
    avio_close(pFormatCtx->pb);
    avformat_free_context(pFormatCtx);

    fclose(jpeg_file);
    fclose(in_file);

    return 0;
}
