//
// Created by opayc on 2023/11/15.
//
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include "../header/util.h"

#define MAX_SIZE 1024
ssize_t readn(int fd, void *vptr, size_t n);

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
 * todo 需要实现当一次读取不全数据的情况
 * @param fd
 * @param buffer
 * @param length
 * @return  读取到的字节数
 */
int full_read(int fd,void *buffer,int length){
    printf("full_read entry\n");
    return read(fd, buffer, length);

//    return readn(fd,buffer,length);

//    int str_len;
//    while ((str_len = read(fd, buffer, length)) != 0){
//        /* 一次读取的消息 */
//    }
}

/**
 * todo 还是有问题？？
 * @param fd
 * @param vptr
 * @param n
 * @return
 */
ssize_t readn(int fd, void *vptr, size_t n)
{
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
