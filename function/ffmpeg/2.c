//
// Created by opayc on 2023/11/27.
//
#include <stdio.h>
#include  <io.h>
/**
 * @return
 */
int main() {
    FILE *file=fopen("D:\\myProjects\\rock_c\\source\\sintel_480x272_yuv420p.yuv","rb");
    int  size  =  filelength(fileno(file));
    printf("size=%d\n",size);

    //size/(WIDTH*HEIGHT*1.5)
    printf("zhennum=%d\n",(int)(size/(480*272*1.5)));
    fclose(file);
    return 1;

}