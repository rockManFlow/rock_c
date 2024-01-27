#include <stdio.h>
#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#include <libavutil/imgutils.h>
#include <libavutil/opt.h>
#include "../../include/ffmpeg/win/libswscale/swscale.h"

/**
 * ����֤���Ǻ��õģ����԰ѷ�װ���ļ�ת��YUV��ָ������������
 * ��ʹ��ffplay���в���Ҳ��������
 * -video_size��С��Ҫ�ر�ע���£��ڳ����л��ӡ��Ӧ��С
 * ffplay -video_size 368x640 -pix_fmt yuv420p test_out_video.yuv
 *
 * YUV+RGB ��Ƶ�Ľ���
 * ��û����
 * mp4-yuv
 * mkv-yuv
 * flv-yuv
 * @return
 */
int main() {
    char *input_url="D:\\myProjects\\rock_c\\source\\test2.mkv";
    char *out_url="D:\\myProjects\\rock_c\\source\\test_out_video_2.yuv";
//    char *out_url="D:\\myProjects\\rock_c\\source\\test_out_video_01.rgb";
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
        if (formatContext->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
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
    // ����֡�������ڱ��������ͼ��
    AVFrame *frame = av_frame_alloc();
    AVFrame *outputFrame = av_frame_alloc();
    // ���ý����ͼ��ĸ�ʽΪYUV420--ת����ĸ�ʽ
    enum AVPixelFormat outputFormat =AV_PIX_FMT_YUV420P;// AV_PIX_FMT_YUV420P
    int outputWidth = codecContext->width;
    int outputHeight = codecContext->height;
    printf("video size outputWidth:%d\n",outputWidth);
    printf("video size outputHeight:%d\n",outputHeight);
    // �����ڴ����ڱ��������ͼ������
    int bufferSize = av_image_get_buffer_size(outputFormat, outputWidth, outputHeight, 1);
    uint8_t *buffer = (uint8_t *) av_malloc(bufferSize);
    //�Ϸ��ڴ�
    av_image_fill_arrays(outputFrame->data, outputFrame->linesize, buffer, outputFormat, outputWidth,outputHeight, 1);

    // ��������ͼ��ת����������
    struct SwsContext *swsContext = sws_getContext(codecContext->width, codecContext->height,codecContext->pix_fmt, codecContext->width, codecContext->height,
                                                   outputFormat, SWS_BICUBIC, NULL, NULL, NULL);

    // ��ת�����ͼ�����ݱ��浽�ļ�
    FILE *outputFile = fopen(out_url, "wb+");
    // ��ȡ��Ƶ֡�����н���͸�ʽת��
    AVPacket *packet=av_packet_alloc();
    while (av_read_frame(formatContext, packet) >= 0) {
        if (packet->stream_index == videoStreamIndex) {
            // ������Ƶ֡
            avcodec_send_packet(codecContext, packet);
            while(avcodec_receive_frame(codecContext, frame)>=0){
                // ��ʽת��
                sws_scale(swsContext, (const uint8_t* const*)frame->data, frame->linesize, 0, codecContext->height,outputFrame->data, outputFrame->linesize);

                //��ʽ1--����YUV420P��ʽ������
                int y_size = codecContext->width * codecContext->height;
                //Y������Ϣ
                fwrite(outputFrame->data[0], 1, y_size, outputFile);
                //ɫ��
                fwrite(outputFrame->data[1], 1, y_size / 4, outputFile);
                //Ũ��
                fwrite(outputFrame->data[2], 1, y_size / 4, outputFile);
                //��ʽ2--����YUV420P��ʽ������
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

            //����RGB24��ʽ������
//            fwrite(outputFrame->data[0],(codecContext->width)*(codecContext->height)*3,1,outputFile);

            //����UYVY��ʽ������
//            fwrite(outputFrame->data[0],(codecContext->width)*(codecContext->height),2,outputFile);
        }
        av_packet_unref(packet);
    }
    // �ͷ���Դ
    fclose(outputFile);
    av_frame_free(&frame);
    av_frame_free(&outputFrame);
    avcodec_free_context(&codecContext);
    avformat_close_input(&formatContext);
    av_free(buffer);
    av_packet_free(&packet);
    return 0;
}