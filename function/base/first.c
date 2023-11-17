//
// Created by opayc on 2023/10/25.
//
#include "../../header/rock.h"
#include <stdio.h>


void show(){
    printf("show str:%s\n",GLOBAL_CONSTANT_STR_1);
    printf("show num:%d\n",GLOBAL_CONSTANT_NUM_1);
    printf("show auto num:%d\n",process);
    printf("show auto str:%s\n",str);
}

int write(int a,char *c){
    process=a;
    str=c;
}

void printTypeSize(){
    printf("TypeSize:%d\n",sizeof(char));//在内存中占用1个字节
    printf("TypeSize:%d\n", sizeof(short int));//在内存中占用2个字节
    printf("TypeSize:%d\n", sizeof(int));//在内存中占用4个字节
    printf("TypeSize:%d\n", sizeof(long));//在内存中占用4个字节
    printf("TypeSize:%d\n", sizeof(long long));//在内存中占用8个字节
    printf("TypeSize:%d\n", sizeof(float));//在内存中占用4个字节
    printf("TypeSize:%d\n", sizeof(double));//在内存中占用8个字节
    struct base_info base;
    base.addr="beijing";
    base.age=20;
    base.phone="+234123498043";
    printf("TypeSize:%d\n", sizeof(base));//返回一个对象或者类型所占的内存字节数
}

int main(){
    printf("start run");
    show();
    char arr[]="zhe is test string";
    write(20,&arr);
    show();
    printf("out arr1:%s\n",arr);

    int aa=111;
    int *p;
    p=&aa;
    *p=*p+1;
    printf("out aa url:%p\n",p);
    printf("out aa:%d\n",*p);


//    while (aa<120){
//        aa=aa+1;
//    }
    int i=0;
    LOOP:
    printf("goto info start\n");
    for(;i<3;i++){
        printf("for run %d\n",i);
        if(i==2){
            i++;
            goto LOOP;
        }
    }

//    do{
//        aa=aa+1;
//    } while (aa<120);

    printTypeSize();

    static int ab=10;
    printf("start end");
}