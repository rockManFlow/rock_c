//
// Created by opayc on 2023/11/15.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include "../../include/libevent/event2/event.h"
#include "../../header/util.h"


void socket_read_cb(int fd, short events, void *null){
    printf("socket_read_cb entry events:%d\n",events);
    char msg[1024];
    int ret=full_read(fd,msg, sizeof(msg));
    if(ret<0){
        perror("client read fail\n");
    } else{
        printf("from server resp:%s\n",msg);
    }
}
/**
 *
 */
void cmd_msg_cb(int in_fd, short events, void* c_fd)
{
    printf("cmd_msg_cb entry events:%d\n",events);
    char msg[100];

    //从输入中端的fd来读取消息
    int ret =full_read(in_fd, msg, sizeof(msg));
    if( ret < 0 ){
        perror("client read cmd fail\n");
        exit(1);
    }

    printf("cmd_msg_cb msg:%s\n",msg);

    if (strncmp(msg, "quit", 4) == 0){
        printf("client exit\n");
        //退出
        exit(1);
    }

    int sockfd = *((int*)c_fd);

    //把终端的消息发送给服务器端
    ret=full_write(sockfd, msg, ret);
    if(ret<0){
        perror("client write error\n");
    } else{
        printf("client write success\n");
    }
}

/**
 * tcp client
 * 1、创建socket
 * 2、进行connect
 * 3、write+read+close
 */

/**
 * 1、仅把响应报文通过事件回调来处理
 * 2、输入数据通过自己实现循环来控制，没使用事件回调方式
 */
int main(){
    printf("client event entry\n");
    int c_fd = socket(AF_INET, SOCK_STREAM, 0);
    if(c_fd<0){
        perror("create client error\n");
        exit(1);
    }

    struct sockaddr_in srv_addr;
    memset(&srv_addr, 0, sizeof(srv_addr));//保险把内存设置为0
    srv_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
    srv_addr.sin_family=AF_INET;
    srv_addr.sin_port= htons(8888);

    int con_result=connect(c_fd,(const struct sockaddr*)&srv_addr, sizeof(srv_addr));
    if(con_result<0){
        perror("client connect error\n");
        exit(1);
    }

    struct event_base *root_event = event_base_new();
    if(root_event==NULL){
        perror("create event base error\n");
        exit(1);
    }

    //添加事件监听，当有服务端响应报文时进行异步处理
    struct event *child_event = event_new(root_event, c_fd,EV_READ|EV_PERSIST,socket_read_cb, NULL);
    event_add(child_event, NULL);

    //监听终端输入事件
    struct event* ev_cmd = event_new(root_event, STDIN_FILENO,EV_READ|EV_PERSIST, cmd_msg_cb,(void*)&c_fd);
    event_add(ev_cmd, NULL);

    printf("client before\n");
    //会阻塞循环，直到退出应用
    int dis_ret=event_base_dispatch(root_event);
    printf("client dis_ret:%d\n",dis_ret);

    //释放资源
    event_base_free(root_event);
    event_free(child_event);
    close(c_fd);

    printf("event client close\n");
    return 0;
}


