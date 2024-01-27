//
// Created by Dell on 2024/1/27.
//

#include <stdio.h>
#define __STDC_CONSTANT_MACROS
//Windows
#ifdef _WIN32
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libavutil/imgutils.h"
#else
//Linux...
#ifdef __cplusplus
#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#endif
#endif

/**
 * yuv编码成指定的文件格式
 * @param argc
 * @param argv
 * @return
 */

int main()
{
    AVFormatContext* pFormatCtx;
    AVOutputFormat* fmt;
    AVStream* video_stream;
    AVCodecContext* pCodecCtx;
    AVCodec* pCodec;

    uint8_t* picture_buf;
    AVPacket pkt;
    int y_size;

    int ret=0;

    int in_w=480,in_h=272;                           //YUV's width and height
    const char* out_file = "D:\\myProjects\\rock_c\\source\\test_input.jpg";    //Output file

    char *input_url="D:\\myProjects\\rock_c\\source\\lena_256x256_yuv420p.yuv";
    FILE *in_file = fopen(input_url, "rb+");

    //Method 1
    pFormatCtx = avformat_alloc_context();
    //Guess format
    fmt = av_guess_format("mjpeg", NULL, NULL);
    pFormatCtx->oformat = fmt;
    //Output URL
    if (avio_open(&pFormatCtx->pb,out_file, AVIO_FLAG_READ_WRITE) < 0){
        printf("Couldn't open output file.");
        return -1;
    }

    //Method 2. More simple
    //avformat_alloc_output_context2(&pFormatCtx, NULL, NULL, out_file);
    //fmt = pFormatCtx->oformat;

    video_stream = avformat_new_stream(pFormatCtx, 0);
    if (video_stream==NULL){
        return -1;
    }

    pCodecCtx = avcodec_alloc_context3(NULL);
    pCodecCtx->codec_id = fmt->video_codec;
    pCodecCtx->codec_type = AVMEDIA_TYPE_VIDEO;
    pCodecCtx->pix_fmt = AV_PIX_FMT_YUV420P;

    pCodecCtx->width = in_w;
    pCodecCtx->height = in_h;

    pCodecCtx->time_base.num = 1;
    pCodecCtx->time_base.den = 25;
    //Output some information
    av_dump_format(pFormatCtx, 0, out_file, 1);
    pCodec = avcodec_find_encoder(fmt->video_codec);
    avcodec_parameters_to_context(pCodecCtx, codecParams);
    if (avcodec_open2(pCodecCtx, pCodec,NULL) < 0){
        printf("Could not open codec.");
        return -1;
    }
    AVFrame* frame = av_frame_alloc();
    //Write Header
    if (avformat_write_header(pFormatCtx, NULL) < 0) {
        printf("无法写入文件头部信息\n");
        return -1;
    }

    y_size = pCodecCtx->width * pCodecCtx->height;
    av_new_packet(&pkt,y_size*3);
    //Read YUV
    size_t size=fread(picture_buf, 1, y_size*3/2, in_file);
    if (size<=0)
    {
        printf("Could not read input file.");
        return -1;
    }
    frame->data[0] = picture_buf;              // Y
    frame->data[1] = picture_buf+ y_size;      // U
    frame->data[2] = picture_buf+ y_size*5/4;  // V

    // 分配内存用于保存解码后的图像数据
    int bufferSize = av_image_get_buffer_size(pCodecCtx->pix_fmt, pCodecCtx->width, pCodecCtx->height, 1);
    uint8_t *buffer = (uint8_t *) av_malloc(bufferSize);
    //瓜分内存
    av_image_fill_arrays(frame->data, frame->linesize, buffer, pCodecCtx->pix_fmt, pCodecCtx->width, pCodecCtx->height, 1);

    //Encode
    ret =avcodec_send_frame(pCodecCtx,frame);
    if(ret < 0){
        printf("Encode Error.\n");
        return -1;
    }
    ret=avcodec_receive_packet(pCodecCtx,&pkt);
    if (ret==1){
        pkt.stream_index = video_stream->index;
        av_write_frame(pFormatCtx, &pkt);
    }

    av_packet_free(&pkt);
    //Write Trailer
    av_write_trailer(pFormatCtx);

    printf("Encode Successful.\n");

    avcodec_close(pCodecCtx);
    av_free(frame);
    av_free(picture_buf);
    avio_close(pFormatCtx->pb);
    avformat_free_context(pFormatCtx);
    fclose(in_file);
    return 0;
}