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
 * yuv�����ָ�����ļ���ʽ--ͼ��ı��룬����ֱ�Ӵ�Դ�ļ�����+��װ��.png���ļ���ʽ
 * ��֤OK
 */

int main(){
    //�������ļ����ش�С��������ò����ʣ��ᵼ�±������ļ������롣ֻ�����ú����ˣ�����������ʾ�������ļ�
    int in_w=256,in_h=256;                           //YUV's width and height
    const char* out_file = "D:\\myProjects\\rock_c\\source\\test_input_1.jpg";    //Output file

    //����ԭ�ļ�������������Ϣ��ʹ��ʲô���뷽ʽ�����ԣ��ؼ�����������ʲô
    char *input_url="D:\\myProjects\\rock_c\\source\\lena_256x256_yuv420p.yuv";
    FILE *in_file = fopen(input_url, "rb+");

    //Method 1
    AVFormatContext* avFormatContext = avformat_alloc_context();
    //Ҫ����ɵĸ�ʽformat--�����ʽ
    AVOutputFormat* avOutputFormat = av_guess_format("mjpeg", NULL, NULL);
    //���������ʽ
    avFormatContext->oformat = avOutputFormat;
    //Output URL ���ڴ�FFmpeg����������ļ� 1���򿪳ɹ��������Ķ��� 2��Ҫ�򿪵��ļ� 3���򿪸�ʽ ����д ���д
    if (avio_open(&avFormatContext->pb,out_file, AVIO_FLAG_READ_WRITE) < 0){
        printf("Couldn't open output file.");
        return -1;
    }

    AVCodec* avCodec = avcodec_find_encoder(avOutputFormat->video_codec);//AV_CODEC_ID_MJPEG

    AVCodecContext* pCodecCtx = avcodec_alloc_context3(avCodec);
    pCodecCtx->codec_id = avOutputFormat->video_codec;
    pCodecCtx->codec_type = AVMEDIA_TYPE_VIDEO;
    pCodecCtx->pix_fmt = AV_PIX_FMT_YUVJ420P;

    pCodecCtx->width = in_w;
    pCodecCtx->height = in_h;

    //���� num������ den����ĸ ����ʱ�����ã�����ʱ��������
    pCodecCtx->time_base.num = 1;
    pCodecCtx->time_base.den = 25;

    if (avcodec_open2(pCodecCtx, avCodec,NULL) < 0){
        printf("Could not open codec.");
        return -1;
    }

    AVFrame* frame = av_frame_alloc();
    //��Щ������ģ���Ȼ����ö�������
    frame->format=pCodecCtx->pix_fmt;
    frame->height=pCodecCtx->height;
    frame->width=pCodecCtx->width;

    // �����ڴ����ڱ��������ͼ������
    int bufferSize = av_image_get_buffer_size(pCodecCtx->pix_fmt, pCodecCtx->width, pCodecCtx->height, 1);
    uint8_t *picture_buf = (uint8_t *) av_malloc(bufferSize);
    //�Ϸ��ڴ�
    av_image_fill_arrays(frame->data, frame->linesize, picture_buf, pCodecCtx->pix_fmt, pCodecCtx->width, pCodecCtx->height, 1);

    AVPacket pkt;
    int y_size = pCodecCtx->width * pCodecCtx->height;
    av_new_packet(&pkt,y_size*3);

    //Read YUV
    size_t size=fread(picture_buf, 1, y_size*3/2, in_file);
    if (size<=0){
        printf("Could not read input file.");
        return -1;
    }

    frame->data[0] = picture_buf;              // Y
    frame->data[1] = picture_buf+ y_size;      // U
    frame->data[2] = picture_buf+ y_size*5/4;  // V

    //Encode
    int ret =avcodec_send_frame(pCodecCtx,frame);
    if(ret < 0){
        printf("Encode Error ret=%d.\n",ret);
        return -1;
    }

    ret=avcodec_receive_packet(pCodecCtx,&pkt);
    if(ret < 0){
        printf("avcodec_receive_packet Error ret=%d.\n",ret);
        return -1;
    }

    printf("write packet to file\n");
    FILE* jpeg_file = fopen(out_file, "wb");
    fwrite(pkt.data, 1, pkt.size, jpeg_file);
    fclose(jpeg_file);

    av_packet_free(&pkt);

    avcodec_close(pCodecCtx);
    av_free(frame);
    av_free(picture_buf);
    avio_close(avFormatContext->pb);
    avformat_free_context(avFormatContext);
    fclose(in_file);

    printf("Encode Successful.\n");
    return 0;
}