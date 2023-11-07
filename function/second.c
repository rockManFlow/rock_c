//
// Created by opayc on 2023/10/31.
//
#include <stdio.h>
#include <malloc/_malloc.h>
#include "../header/call.h"

int length=0;
int moveAfterOne(int *a,int startIndex,int endIndex);

/**
 * 将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。
 * 即：先将序列的第1个记录看成是一个有序的子序列，
 * 然后从第2个记录逐个进行插入，直至整个序列有序为止。
 * 插入排序-升序-OK
 */
void insertSort(int *a){
    for(int j=1;j<length;j++){
        for(int i=j-1;i>=0;i--){
            if(a[i]<=a[j]){
                //从i之后到index-1都往后移动一位
                moveAfterOne(a,i+1,j);
                break;
            }
            if(a[i]>a[j]){
                if(i==0){
                    moveAfterOne(a,i,j);
                }
                continue;
            }
        }
    }
}

/**
 * 往后移动一位
 * @param a
 * @param startIndex
 * @param endIndex
 * @return
 */
int moveAfterOne(int *a,int startIndex,int endIndex){
    if(startIndex==endIndex){
        return 0;
    }
    if(startIndex>endIndex||startIndex<0||endIndex>length-1){
        printf("move fail:illegal param");
        return -1;
    }

    int v=a[endIndex];

    for(int i=endIndex-1;i>=startIndex;i--){
        a[i+1]=a[i];
    }
    a[startIndex]=v;

    return 0;
}

void memory(){
    //数组大小必须提前指定，并不可更改，只能在程序或者函数运行完成时，系统给释放
    //使用数组来存储字符串，数据大小最后得有一个位置来保存\0，字符串既是以\0结尾的字符数组
    char a[10]="123456789";//特殊数组，表示字符串
    char b[5]={'1','2','3','4','5'};//数组
    printf("a:%s\n",a);
    printf("b:%s\n",b);

    int *p = malloc(sizeof*p);
    *p = 10;
    printf("p1 = %p\n", p);
    //当指针变量被释放后，它所指向的内存空间中的数据会怎样呢？free 的标准行为只是表示这块内存可以被再分配，至于它里面的数据是否被清空并没有强制要求。
    free(p);
    printf("p4 = %d\n", *p);
    printf("p2 = %p\n", p);
    *p=20;
    printf("p3 = %d\n", *p);
    p=NULL;
    /**
     *  p1 = 0x600002ee0030
        p4 = -1156644816
        p2 = 0x600002ee0030
        p3 = 20
    释放前后，p 所指向的内存空间是一样的。所以释放后 p 所指向的仍然是那块内存空间。
     既然指向的仍然是那块内存空间，那么就仍然可以往里面写数据。可是释放后该内存空间已经不属于它了，
     该内存空间可能会被分配给其他变量使用。如果其他变量在里面存放了值，而你现在用 p 往里面写入数据就会把那个值给覆盖，
     这样就会造成其他程序错误，所以当指针变量被释放后，要立刻把它的指向改为 NULL。
     */
}

int close_file(FILE *fp){
    int r=fclose(fp);
    if(r==0){
        printf("file close success\n");
        return 0;
    }else if(r==EOF){
        printf("file close fail\n");
        return -1;
    }
    return 1;
}

void file_read_c(FILE *fp){
    //1、通过一个一个字符方式来读取文件-OK
    //fputc()和putc()用于向文件写入一个字符
    int c;
    while ((c=fgetc(fp))!=EOF){
        printf("%c",c);
    }
    printf("\n");

    //2、fputs()函数用于向文件写入字符串-OK
    char st[8];//接收字符串，每次读取6个大小
    while (fgets(st,6,fp)!=NULL){
        printf("%s",st);
    }
    printf("\n");

    //3、复制写文件-OK
    FILE *fp2;
    char st1[8];//接收字符串，每次读取6个大小
    while (fgets(st1,6,fp)!=NULL){
        fputs(st,fp2);
    }

    //4、读写二进制文件-OK
    char fileUrl[255]="/Users/opayc/products/rock_c/source/ffmpeg-6.0.tar.xz";
//    FILE *fp=fopen(fileUrl,"rb");
//
//    FILE *fp2=fopen("/Users/opayc/products/rock_c/source/ffmpeg-6.0-backup.tar.xz","a+b");
    char arr[10];
    int length=sizeof(arr) / sizeof(arr[0]);
    while (fread(arr,sizeof(arr[0]),length,fp)==length){
        fwrite(arr,sizeof(arr[0]),length,fp2);
    }
}

/**
 * todo 文件读写及io操作
 * fwrite()用来一次性写入较大的数据块，主要用途是将数组数据一次性写入文件，适合写入二进制数据。
 * 写入数组arr
 * fwrite(
  arr,
  sizeof(arr[0]),
  sizeof(arr) / sizeof(arr[0]),
  fp
);
 fread()，比较适合读写二进制数据。---写法和fwrite一样

 feof()函数判断文件的内部指针是否指向文件结尾。
 fseek() 记录当前打开的文件的读写位置（file position），即下一次读写从哪里开始
 */
void file_io(){
    //读写二进制文件
    char fileUrl[255]="/Users/opayc/products/rock_c/source/ffmpeg-6.0.tar.xz";
    FILE *fp=fopen(fileUrl,"rb");

    FILE *fp2=fopen("/Users/opayc/products/rock_c/source/ffmpeg-6.0-backup.tar.xz","a+b");

    char arr[10];
    int length=sizeof(arr) / sizeof(arr[0]);
    while (fread(arr,sizeof(arr[0]),length,fp)==length){
        fwrite(arr,sizeof(arr[0]),length,fp2);
    }

    close_file(fp2);

    //??
//    char words[3];
//    while (fscanf(fp, "%s", words)==1){
//        printf("%s",words);
//        printf("\n");
//    }
//    printf("\n");



    close_file(fp);

}



int main(){
    /**int a[]={8,10,2,11,5,3,2};
    length=(sizeof(a) / sizeof(int));
    printf("%d\n",length);
    insertSort(&a);
    for(int i=0;i<length;i++){
        printf("index:%d,value:%d\n",i,a[i]);
    }

    char arr[]="1234567";
    show(&arr);
    printf("main arr %s\n",arr);
    unsigned int size=write(arr,10);
    printf("size %d\n",size);

    memory();**/

    file_io();
}
