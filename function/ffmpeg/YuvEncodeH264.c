//
// Created by Dell on 2024/1/31.
//

#include <stdio.h>
#include <libavutil/imgutils.h>
#include "libavutil\opt.h"
#include "libavcodec\avcodec.h"
#include "libavformat\avformat.h"
#include "libswscale\swscale.h"

/**
 * ��֤���ԣ�û����-�ؼ����ǿ�͸߼�֡��Ҫ��ȷ�����򲥷�������
 * @return
 */
int main()
{
    FILE *in_file = fopen("D:\\myProjects\\rock_c\\source\\sintel_480x272_yuv420p.yuv", "rb");	//Input YUV data ��ƵYUVԴ�ļ�
    int in_w=480,in_h=272;//�ؼ���-���
    //Frames to encode---֡����=size/(WIDTH*HEIGHT*1.5)
    int framenum=125;//�ؼ���
    const char* out_file = "D:\\myProjects\\rock_c\\source\\sintel_480x272_yuv420p_2.h264";	//Output Filepath ����ļ�·��

    //Method1 ����1.���ʹ�ü�������
//    AVFormatContext* pFormatCtx = avformat_alloc_context();
    //Guess Format �¸�ʽ
//    AVOutputFormat* fmt = av_guess_format(NULL, out_file, NULL);
//    pFormatCtx->oformat = fmt;

    //Method 2 ����2.�����Զ���һЩ
    AVFormatContext* pFormatCtx;
    //��ʼ�����������AVFormatContext
    avformat_alloc_output_context2(&pFormatCtx, NULL, NULL, out_file);
    AVOutputFormat *fmt = pFormatCtx->oformat;


    //������ļ� ע�����·��
    if (avio_open(&pFormatCtx->pb,out_file, AVIO_FLAG_READ_WRITE) < 0)
    {
        printf("Failed to open output file!");
        return -1;
    }

    //������������������ʽ������
    AVStream* video_st = avformat_new_stream(pFormatCtx, 0);
    video_st->time_base.num = 1;
    video_st->time_base.den = 25;

    if (video_st==NULL)
    {
        printf("Failed to avformat_new_stream!");
        return -1;
    }
    //Param that must set
    AVCodec* avCodec = avcodec_find_encoder(pFormatCtx->oformat->video_codec);
    if (!avCodec){
        printf("Can not find encoder!\n");
        return -1;
    }

    AVCodecContext* pCodecCtx = avcodec_alloc_context3(avCodec);
    //pCodecCtx->codec_id =AV_CODEC_ID_HEVC;
    pCodecCtx->codec_id = fmt->video_codec;
    pCodecCtx->codec_type = AVMEDIA_TYPE_VIDEO;
    pCodecCtx->pix_fmt = AV_PIX_FMT_YUV420P;
    pCodecCtx->width = in_w;
    pCodecCtx->height = in_h;
    pCodecCtx->time_base.num = 1;
    pCodecCtx->time_base.den = 25;
    pCodecCtx->bit_rate = 400000;// ����
    pCodecCtx->gop_size=250;// ÿ250֡����һ���ؼ�֡
    //H264
//    pCodecCtx->me_range = 16;
//    pCodecCtx->max_qdiff = 4;
//    pCodecCtx->qcompress = 0.6;
    pCodecCtx->qmin = 10;
    pCodecCtx->qmax = 51;

    //Optional Param
    pCodecCtx->max_b_frames=3;

    // Set Option
    AVDictionary *param = 0;
    //H.264
    if(pCodecCtx->codec_id == AV_CODEC_ID_H264) {
        av_dict_set(&param,"preset", "slow", 0);
        av_dict_set(&param, "tune", "zerolatency", 0);
    }
    //H.265
    if(pCodecCtx->codec_id == AV_CODEC_ID_H265){
        av_dict_set(&param, "x265-params", "qp=20", 0);
        av_dict_set(&param, "preset", "ultrafast", 0);
        av_dict_set(&param, "tune", "zero-latency", 0);
    }

    //�򿪱�����
    if (avcodec_open2(pCodecCtx, avCodec,&param) < 0){
        printf("Failed to open encoder!\n");
        return -1;
    }

    AVFrame* frame = av_frame_alloc();
    frame->format=pCodecCtx->pix_fmt;
    frame->height=pCodecCtx->height;
    frame->width=pCodecCtx->width;
    // �����ڴ����ڱ��������ͼ������
    int bufferSize = av_image_get_buffer_size(pCodecCtx->pix_fmt, pCodecCtx->width, pCodecCtx->height, 1);
    uint8_t *picture_buf = (uint8_t *) av_malloc(bufferSize);
    //�Ϸ��ڴ�
    av_image_fill_arrays(frame->data, frame->linesize, picture_buf, pCodecCtx->pix_fmt, pCodecCtx->width, pCodecCtx->height, 1);

    //Write File Header д�ļ�ͷ
    avformat_write_header(pFormatCtx,NULL);

    AVPacket pkt;
    int y_size = pCodecCtx->width * pCodecCtx->height;
    av_new_packet(&pkt,y_size*3);

    FILE* jpeg_file = fopen(out_file, "wb");
    for (int i=0; i<framenum; i++){
        //Read YUV ����YUV
        if (fread(picture_buf, 1, y_size*3/2, in_file) < 0){
            printf("Failed to read YUV data!\n");
            return -1;
        }else if(feof(in_file)){
            break;
        }
        //����дһ֡YUV����
        frame->data[0] = picture_buf;  // ����Y
        frame->data[1] = picture_buf+ y_size;  // U
        frame->data[2] = picture_buf+ y_size*5/4; // V
        //PTS
        frame->pts=i;
        //Encode ����
        int ret =avcodec_send_frame(pCodecCtx,frame);
        if(ret < 0){
            printf("Encode Error ret=%d.\n",ret);
            return -1;
        }

        ret=avcodec_receive_packet(pCodecCtx,&pkt);
        if(ret == 0){
            fwrite(pkt.data, 1, pkt.size, jpeg_file);
        }
        av_packet_unref(&pkt);
    }

    //Write file trailer д�ļ�β
    av_write_trailer(pFormatCtx);

    //Clean ����
    avcodec_close(pCodecCtx);
    av_free(frame);
    av_free(picture_buf);
    avio_close(pFormatCtx->pb);
    avformat_free_context(pFormatCtx);

    fclose(jpeg_file);
    fclose(in_file);

    return 0;
}