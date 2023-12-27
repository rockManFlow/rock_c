/**
 * todo 音频采样源数据处理
 * https://blog.csdn.net/leixiaohua1020/article/details/50534316
 */

#include <stdio.h>
#include <stdlib.h>


/**
 * 将PCM16LE双声道数据中左声道和右声道的数据分离成两个文件
 * Split Left and Right channel of 16LE PCM file.
 * @param url  Location of PCM file.
 *
 */
int simplest_pcm16le_split(char *url){
    FILE *fp=fopen(url,"rb+");
    FILE *fp1=fopen("output_l.pcm","wb+");
    FILE *fp2=fopen("output_r.pcm","wb+");

    unsigned char *sample=(unsigned char *)malloc(4);

    while(!feof(fp)){
        fread(sample,1,4,fp);
        //L：左声道
        fwrite(sample,1,2,fp1);
        //R：右声道
        fwrite(sample+2,1,2,fp2);
    }

    free(sample);
    fclose(fp);
    fclose(fp1);
    fclose(fp2);
    return 0;
}

/**
 * 将PCM16LE双声道数据中左声道的音量降低一半：振幅减半
 * Halve volume of Left channel of 16LE PCM file
 * @param url  Location of PCM file.
 */
int simplest_pcm16le_halfvolumeleft(char *url){
    FILE *fp=fopen(url,"rb+");
    FILE *fp1=fopen("output_halfleft.pcm","wb+");

    int cnt=0;

    unsigned char *sample=(unsigned char *)malloc(4);

    while(!feof(fp)){
        short *samplenum=NULL;
        fread(sample,1,4,fp);

        samplenum=(short *)sample;
        *samplenum=*samplenum/2;
        //L todo？？
        fwrite(sample,1,2,fp1);
        //R
        fwrite(sample+2,1,2,fp1);

        cnt++;
    }
    printf("Sample Cnt:%d\n",cnt);

    free(sample);
    fclose(fp);
    fclose(fp1);
    return 0;
}

/**
 * 将PCM16LE双声道音频采样数据的声音速度提高一倍
 * Re-sample to double the speed of 16LE PCM file
 * @param url  Location of PCM file.
 */
int simplest_pcm16le_doublespeed(char *url){
    FILE *fp=fopen(url,"rb+");
    FILE *fp1=fopen("output_doublespeed.pcm","wb+");

    int cnt=0;

    unsigned char *sample=(unsigned char *)malloc(4);

    while(!feof(fp)){

        fread(sample,1,4,fp);

        //todo 只保存了奇数点的样值，偶数值没保存，数据量减少一半，相当于声音速度提升了2倍
        if(cnt%2!=0){
            //L
            fwrite(sample,1,2,fp1);
            //R
            fwrite(sample+2,1,2,fp1);
        }
        cnt++;
    }
    printf("Sample Cnt:%d\n",cnt);

    free(sample);
    fclose(fp);
    fclose(fp1);
    return 0;
}

/**
 * 将PCM16LE双声道音频采样数据转换为PCM8音频采样数据
 * 通过计算的方式将PCM16LE双声道数据16bit的采样位数转换为8bit
 * Convert PCM-16 data to PCM-8 data.
 * @param url  Location of PCM file.
 */
int simplest_pcm16le_to_pcm8(char *url){
    FILE *fp=fopen(url,"rb+");
    FILE *fp1=fopen("output_8.pcm","wb+");

    int cnt=0;

    unsigned char *sample=(unsigned char *)malloc(4);

    /**
     * 左声道与右声道存储格式：1字节（左）+1字节（右）+1字节（左）+1字节（右）依次类推
     */
    while(!feof(fp)){

        short *samplenum16=NULL;//short占2字节
        char samplenum8=0;//占1个字节
        unsigned char samplenum8_u=0;
        //todo 这个仅是示例，仅取了前四个字节的数据
        fread(sample,1,4,fp);
        //(-32768-32767)
        samplenum16=(short *)sample;
        samplenum8=(*samplenum16)>>8;
        //(0-255)
        samplenum8_u=samplenum8+128;
        //L
        fwrite(&samplenum8_u,1,1,fp1);

        samplenum16=(short *)(sample+2);
        samplenum8=(*samplenum16)>>8;
        samplenum8_u=samplenum8+128;
        //R
        fwrite(&samplenum8_u,1,1,fp1);
        cnt++;
    }
    printf("Sample Cnt:%d\n",cnt);

    free(sample);
    fclose(fp);
    fclose(fp1);
    return 0;
}

/**
 * 将从PCM16LE单声道音频采样数据中截取一部分数据
 * Cut a 16LE PCM single channel file.
 * @param url        Location of PCM file.
 * @param start_num  start point
 * @param dur_num    how much point to cut
 */
int simplest_pcm16le_cut_singlechannel(char *url,int start_num,int dur_num){
    FILE *fp=fopen(url,"rb+");
    FILE *fp1=fopen("output_cut.pcm","wb+");
    FILE *fp_stat=fopen("output_cut.txt","wb+");

    unsigned char *sample=(unsigned char *)malloc(2);

    int cnt=0;
    while(!feof(fp)){
        fread(sample,1,2,fp);
        if(cnt>start_num&&cnt<=(start_num+dur_num)){
            fwrite(sample,1,2,fp1);

            short samplenum=sample[1];
            samplenum=samplenum*256;
            samplenum=samplenum+sample[0];

            fprintf(fp_stat,"%6d,",samplenum);
            if(cnt%10==0)
                fprintf(fp_stat,"\n",samplenum);
        }
        cnt++;
    }

    free(sample);
    fclose(fp);
    fclose(fp1);
    fclose(fp_stat);
    return 0;
}

int main(){

    /**
     * 声音样值的采样频率一律是44100Hz，采样格式一律为16LE。“16”代表采样位数是16bit。由于1Byte=8bit，所以一个声道的一个采样值占用2Byte。
     */
    simplest_pcm16le_split("NocturneNo2inEflat_44.1k_s16le.pcm");

    /**
     *
     */
    simplest_pcm16le_halfvolumeleft("NocturneNo2inEflat_44.1k_s16le.pcm");

    /**
     * 采样了每个声道奇数点的样值。处理完成后，原本22秒左右的音频变成了11秒左右。音频的播放速度提高了2倍，音频的音调也变高了很多。
     * 保存偶数值也行
     */
    simplest_pcm16le_doublespeed("NocturneNo2inEflat_44.1k_s16le.pcm");

    /**
     * PCM16LE格式的采样数据的取值范围是-32768到32767，而PCM8格式的采样数据的取值范围是0到255。
     * 所以PCM16LE转换到PCM8需要经过两个步骤：
     * 第一步是将-32768到32767的16bit有符号数值转换为-128到127的8bit有符号数值，
     * 第二步是将-128到127的8bit有符号数值转换为0到255的8bit无符号数值。
     * 在本程序中，16bit采样数据是通过short类型变量存储的，而8bit采样数据是通过unsigned char类型存储的。
     */
    simplest_pcm16le_to_pcm8("NocturneNo2inEflat_44.1k_s16le.pcm");

    /**
     * 可以从PCM数据中选取一段采样值保存下来，并且输出这些采样值的数值。
     * 上述代码运行后，会把单声道PCM16LE格式的“drum.pcm”中从2360点开始的120点的数据保存成output_cut.pcm文件。
     */
    simplest_pcm16le_cut_singlechannel("drum.pcm",2360,120);
}
