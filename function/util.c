//
// Created by opayc on 2023/11/15.
//
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include "../header/util.h"
#include <string.h>

ssize_t readn(int fd, void *vptr, size_t n);

/**
 * 写指定长度的数据，直到全部写完成
 * @param fd
 * @param buffer
 * @param length 指定长度数据
 * @return
 */
int full_write(int fd,void *buffer,int length){
    printf("full_write entry\n");
    int bytes_left;
    int written_bytes;
    char *ptr;

    ptr=buffer;
    bytes_left=length;
    while(bytes_left>0)
    {
        //write写部分或全部就会返回，不一定是发送完之后才返回
        written_bytes=write(fd,ptr,bytes_left);
        if(written_bytes<=0)
        {
            if(errno==EINTR)
                written_bytes=0;
            else
                return(-1);
        }
        bytes_left-=written_bytes;
        ptr+=written_bytes;
    }
    return(0);
}

/**
 * 是否需要在底层做读的拆包和粘包
 * @param fd
 * @param buffer
 * @param length
 * @return  读取到的字节数
 */
int full_read(int fd,void *buffer,int length){
    printf("full_read entry\n");
    return read(fd, buffer, length);
}

/**
 * 读取指定长度的报文，没全部读到，不会返回
 * @param fd
 * @param buffer
 * @param length
 * @return
 */
int full_read_size(int fd,void *buffer,int length){
    return readn(fd,buffer,length);
}

/**
 * 读取指定长度的报文，没全部读到，不会返回
 * 整体逻辑代码没问题
 * @param fd
 * @param vptr
 * @param n 必须读取到这个长度之后才能返回
 * @return
 */
ssize_t readn(int fd, void *vptr, size_t n){
    size_t nleft;
    ssize_t nread;
    char *ptr;

    ptr = vptr;
    nleft = n;
    while (nleft > 0) {
        if ( (nread = read(fd, ptr, nleft)) < 0) {
            if (errno == EINTR){
                nread = 0;        /* and call read() again */
            }
            else{
                return(-1);
            }
        } else if (nread == 0){
            break;                /* EOF */
        }
        nleft -= nread;
        ptr   += nread;
    }
    return(n - nleft);        /* return >= 0 */
}

//int main(){
//    while (1){
//        printf("please input:");
//        char data[6];
//        unsigned int size=full_read(STDIN_FILENO,data, sizeof(data));
//        printf("size=%d,data=%s\n",size,data);
//        fgets(data, sizeof(data),stdin);
//        if (strncmp(data, "quit", 4) == 0){
//            break;
//        }
//    }
//}
