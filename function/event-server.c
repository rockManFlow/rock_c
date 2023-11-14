#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include "../include/libevent/event2/event.h"

#define MAX 1000

//定义一个fd和事件的映射结构体
struct event_fd
{
    evutil_socket_t fd;
    struct event *ev;
}event[MAX];
/**
 * 初始化事件和文件句柄
 */
void init_ev_fd()
{
    int i = 0;
    for(i=0; i< MAX;i++)
    {
        event[i].fd = -1;
        event[i].ev = NULL;
    }
}

void setEventFd(evutil_socket_t fd,struct event *ev)
{
    int i = 0;
    //查找存放的位置
    for(i = 0;i<MAX; i++)
    {
        if(event[i].fd == -1)
        {
            break;
        }
    }
    //若找不到合适的位置，直接退出进程
    if(i==MAX)
    {
        exit(1);
    }

    event[i].fd = fd;
    event[i].ev = ev;
}

int findEv(int fd)
{
    int i = 0;
    for(i=0;i<MAX;i++)
    {
        if(event[i].fd == fd)
        {
            break;
        }
    }
    if(i==MAX)
    {
        printf("not find fd\n");
        exit(1);
    }

    return i;
}
/**
 * 事件固定回调函数格式
 * @param connfd  fd
 * @param events  事件
 * @param args  base事件
 */
void readcb(evutil_socket_t connfd, short events, void *args)
{
    char buf[1024];
    memset(buf, 0x00, sizeof(buf));

    int num = findEv(connfd);
    //网络读数据，返回读取的字节数，无返回-1
    int n = read(connfd, buf, sizeof(buf));
    if(n<0)
    {
        perror("read error");
        //关闭fd
        close(connfd);
        //删除事件
        event_del(event[num].ev);//将通信文件描述符对应的事件从base地基上删除
        event[num].fd = -1;
        event[num].ev = NULL;
    }
    else if(n==0)
    {
        printf("client close\n");
        close(connfd);
        event_del(event[num].ev);
        printf("[%p],[%d]\n",event[num].ev,num);
        event[num].fd = -1;
        event[num].ev = NULL;
    }
    else
    {
        printf("received from client info:%s",buf);
        for(int i=0;i<n;i++)
        {
            //toupper 把小写字母转换为大写字母
            buf[i] = toupper(buf[i]);
        }
        printf("send response:%s",buf);
        //写响应把缓存中的数据往fd写
        write(connfd, buf, strlen(buf));
    }
}

//设置有监听事件发生时的回调函数---固定入参格式，监听的fd，事件，base事件
void conncb(evutil_socket_t sockfd, short events, void *args)
{
    struct event_base *base = (struct event_base *)args;

    //有新的连接请求，调用accept接受客户端连接，放到cliaddr结构体中
    struct sockaddr_in cliaddr;
    socklen_t addrlen = sizeof(struct sockaddr_in);
    //4、接受新的客户端连接，并创建新的连接socket fd
    int connfd = accept(sockfd, (struct sockaddr*)&cliaddr,&addrlen);
    if(connfd<0)
    {
        perror("accept error");
        exit(1);

    }

    //打印客户端IP和PORT
    char sIP[128];
    memset(sIP,0x00,sizeof(sIP));
    printf("received from %s at port %d\n",inet_ntop(AF_INET,&cliaddr.sin_addr,sIP,sizeof(sIP)),ntohs(cliaddr.sin_port));

    //将新的文件描述符上树----并发驱动事件读取数据
    struct event *readev = event_new(base, connfd, EV_READ|EV_PERSIST, readcb, base);
    event_add(readev, NULL);
    //连接fd与event做映射
    setEventFd(connfd,readev);
}

int main()
{

    //初始化事件和文件描述符映射表
    init_ev_fd();

    //1、创建socket AF_INET：IPv4 。SOCK_STREAM：流式 tcp协议
    int lfd = socket(AF_INET, SOCK_STREAM, 0);

    //设置端口复用
    int opt = 1;
    setsockopt(lfd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    //绑定
    struct sockaddr_in serv;
    bzero(&serv, sizeof(serv));
    serv.sin_addr.s_addr = htonl(INADDR_ANY);//IP 把无符号长整形 host转网络
    serv.sin_port = htons(8888);//port 把无符号短整形 host转网络
    serv.sin_family = AF_INET;//协议族 IPv4
    //2、把socket文件句柄绑定到网络信息
    bind(lfd, (struct sockaddr*)&serv, sizeof(serv));

    //3、监听端口及设置最大连接数
    listen(lfd, 120);

    //11、创建事件地基
    struct event_base *base = event_base_new();
    if(base==NULL)
    {
        printf("event_base_new error\n");
        return -1;
    }

    //22、创建监听文件描述符对应的事件--并发事件驱动连接事件
    struct event *ev = event_new(base, lfd, EV_READ|EV_PERSIST, conncb, base);
    if(ev==NULL)
    {
        printf("event_new error\n");
        return -1;
    }

    //33、将新的事件节点上base地基
    event_add(ev, NULL);

    //44、进入事件循环等待---相当于while(1)
    event_base_dispatch(base);

    //55、释放资源
    event_base_free(base);
    event_free(ev);

    close(lfd);
    return 0;


}
