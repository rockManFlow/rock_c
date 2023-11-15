##### C相关框架学习

### libevent

#### libevent介绍
优点：
主要用于处理输入输出，用于并发处理IO事件通知的框架。处理的问题包括非阻塞，异步，超时，同时避免了使用多线程时可能带来的死锁问题。  
之所以称为libevent，因为这种并发模型被称为是事件驱动的或数据流的方式。  
libevent API 提供了一种机制，用于在文件描述符上发生特定事件或达到超时后执行回调函数。
缺点：
本身是单线程的，依赖于系统提供的特定输入输出接口。类似的还有libel,libuv

*基于reactor模式的实现，关键在于IO事件的回调机制。*

待学习的部分：https://blog.csdn.net/skytering/article/details/104295406

--------------------------------------------
#### 网络编程
socket：网络也是一种特殊的IO，也是一种文件描述符。  
socket的类型：  
* SOCK_STREAM(标准套接字)：流式套接字使用的是TCP协议，保证可靠性。    
+ SOCK_DGRAM(标准套接字)：数据报套接字使用的是UDP协议，不保证是可靠。  
- SOCK_RAW(原始套接字):原始套接字是在传输层及传输层以下使用的套接字.--管理员权限
标准套接字在接收和发送时只能操作数据部分（TCP Payload / UDP Payload），而不能对 IP 首部或TCP 首部和 UDP 首部进行操作。
原始套接字在接收和发送时不仅能操作数据部分（TCP Payload / UDP Payload），也能对 IP 首部或TCP 首部和 UDP 首部进行操作。
C中两个比较重要的struct，sockaddr和sockaddr_in，这两个结构体都是用用来存储socket的相关信息的  
```
struct sockaddr {
    unsigned short sa_family;//地址族，2字节
    char sa_data[14];//14字节的协议地址，包含该socket的IP地址和端口号，14字节
}

struct sockaddr_in {
    short sa_family;//地址族，2字节
    unsigned short int sin_port;//端口号，2字节
    struct in_addr sin_addr;//IP地址，4字节
    unsigned char sin_zero[];//填充0以保持与struct sockaddr同样大小，8字节
}

两种结构体都存在sa_family字段，sa_family可选的常见值定义在#include < netinet/in.h >文件下，其中可选的值有以下几种：

```
通过函数inet_addr和inet_ntoa可以实现点分字符串与网络字节顺序格式IP地址之间的转换。  
htons和ntohs完成16位无符号数的相互转换，htonl和ntohl完成32位无符号数的相互转换。  
gethostbyname、gethostbyaddr、getaddrinfo等，它们都可以实现IPv4和IPv6的地址和主机名之间的转换。  

tcp网络编程server端主要步骤
1. 创建socket对象，获取到对应的sfd文件句柄
2. 绑定bind方法：将 socket 与本地 IP 地址和端口绑定
3. 监听listen：监听端口
4. accept：获得 连接 socket，阻塞等待客户端发起连接
5. 调用read()：阻塞等待客户端发送的数据请求，收到请求后从 read() 返回，根据连接句柄cfd，存到本机buf中，之后程序从buf中取数据进行其他操作。--接收  
6. 调用write()：进行发送
7. 关闭连接：当 read() 返回 0 的时候，说明客户端发来了 FIN 数据包，即关闭连接，调用 close() 关闭 连接 socket 和 监听 socket

client端步骤
1. 创建socket：创建客户端socket
2. 进行连接connect:向服务器发送建立连接请求
3. 调用 write():向服务器发送数据
4. 调用 read():阻塞等待服务器应答
5. 调用 close(): 关闭 客户端 socket ，即关闭连接，向服务器发送 FIN 数据报

udp server端步骤
1. 调用 socket():分配文件描述符，创建 服务器 socket
2. 调用 bind():将 socket 与本地 IP 地址和端口绑定
3. 调用 recvfrom()，阻塞，接受客户端的数据 
4. 调用 sendto()，将数据发送给客户端
5. 调用 close() 关闭 服务器 socket

client端步骤
1. 调用 socket()，分配文件描述符，创建 客户端 socket
2. 调用 sendto()，向服务器发送数据 
3. 调用 recvfrom()，阻塞，接受服务器的数据 
4. 调用 close() 关闭 客户端 socket ，即关闭连接
````
socket：
头文件：#include < sys/socket.h>
函数原型：int socket(int family,int type,int protocol)
参数含义：
family：对应的就是AF_INET、AF_INET6等。
    AF_INET：IPv4协议
    AF_INET4：IPv6协议
    AF_LOCAL：UNIX域协议
    AF_LINK：链路地址协议
    AF_KEY：秘钥套接字
type：套接字类型：SOCK_STREAM、SOCK_DGRAM、SOCK_RAW。
protocol：指定协议，如下所示：  
    IPPROTO_IP（0）：接受 TCP 类型的数据帧
    
    IPPROTO_ICMP（1）：接受 ICMP 类型的数据帧
    
    IPPROTO_IGMP（2）接受 IGMP 类型的数据帧
    
    IPPROTO_TCP（6）：接受 TCP 类型的数据帧
    
    IPPROTO_UDP（17）：接受 UDP 类型的数据帧
    
    ETH_P_IP（0x800）：接收发往本机 MAC 的 IP 类型的数据帧
    
    ETH_P_ARP（0x806）：接受发往本机 MAC 的 ARP 类型的数据帧
    
    ETH_P_RARP（0x8035）：接受发往本机 MAC 的 RARP 类型的数据帧
    
    ETH_P_ALL（0x3）：接收发往本机 MAC 的所有类型 IP ARP RARP 的数据帧，接收从本机发出的所有类型的数据帧。
        (混杂模式打开的情况下，会接收到非发往本地 MAC 的数据帧)

返回值：
成功：非负套接字描述符。
出错：-1

bind：
头文件：#include< sys/socket.h>
函数原型：int bind(int sockfd,struct sockaddr *my_addr,int addrlen)
    参数含义：
    sockfd：套接字描述符。
    my_addr：本地地址信息。
    addrlen：地址长度：
返回值：
    成功：0
    出错：-1

listen：
头文件：#include < sys/socket.h>
函数原型：int listen(int sockfd,int backlog)
参数含义：
    sockfd：套接字描述符
    backlog：请求队列中允许的最大请求数-最大连接，大多数系统默认为20
返回值：
    成功：0
    出错：-1

accept：
头文件：#include < sys/socket.h>
函数原型：int accept(int sockfd,struct sockaddr * addr,socklen_t* addrlen)
作用：
    提取出 监听 socket 的等待连接队列中获取请求，创建 一个新的 socket，即 连接 socket
    新建立的 连接 socket 用于发送数据和接受数据。
参数含义：
    sockfd：监听 socket 套接字描述符
    addr：地址信息-用于存储接收到的client信息
    addrlen：地址长度
返回值：
    成功：0
    出错：-1

connect：
头文件：#include < sys/socket.h>
函数原型：int connect(int sockfd,struct sockaddr* serv_addr,int addrlen)
参数含义：
    sockfd：client的套接字描述符
    serv_addr：要连接的服务器端地址
    addrlen：地址长度
返回值：
    成功：0
    出错：-1

send
头文件：#include < sys/socket.h>
函数原型：int send(int sockfd,const void* msg,int len,int flags)
参数含义：
    sockfd：套接字描述符
    msg：指向要发送数据的指针
    len：数据长度
    flags：一般为0
返回值：
    成功：发送的字节数
    出错：-1

recv
头文件：#include < sys/socket.h>
函数原型：int recv(int sockfd,void* buf,int len,unsigned int flags)
参数含义：
    sockfd：套接字描述符
    buf：存放接受数据的缓冲区
    len：数据长度
    flags：一般为0
返回值：
    成功：接受的字节数
    出错：-1

read
函数原型：ssize_t read(int fd,void*buf,size_t count)
参数含义：
    fd:socket的文件描述符
    buf:存放读出的数据的缓冲区
    count:每次读取的最大字节数
返回值：
    读取成功返回读出的字节数
    失败返回-1
tip:
    1、堵塞的情况
    在调用read函数过后，程序就会一直去接收缓存里面拿消息，如果没有消息发送过来，那么程序就会一直堵塞在read函数处等待消息的到来，
    也不会去执行后面的代码，除非对面发送了消息或者关闭了socket连接（其实关闭连接相当于也是发送了FIN消息过来），那么程序将会继续执行后续的操作。

    2、读不完全的情况
    因为read函数在收到一次消息后就会继续执行后续代码，那么可能存在两种情况，让程序没法收齐消息：
        1、发送的消息长度超过了read()的第三个参数（即读取的最大字节数），这个时候也只会读到最大长度的消息，再往后的消息就不会再读取了。
        2、报文太长，分包发送或者对方发了几次包，前面的包到了后面的包还没到，然后read()函数也执行完了就不再去read了。

write
函数原型：write(int fd, const void*buf,size_t nbytes);

write函数将buf中的nbytes字节内容写入文件描述符fd.成功时返回写的字节数.失败时返回-1. 并设置errno变量. 
在网络程序中,当我们向套接字文件描述符写时有两可能.
1)write的返回值大于0,表示写了部分或者是全部的数据. 这样我们用一个while循环来不停的写入，但是循环过程中的buf参数和nbyte参数得由我们来更新。
    也就是说，网络写函数是不负责将全部数据写完之后在返回的。
2)返回的值小于0,此时出现了错误.我们要根据错误类型来处理.
    如果错误为EINTR表示在写的时候出现了中断错误.
    如果为EPIPE表示网络连接出现了问题(对方已经关闭了连接).



sendto
头文件：#include < sys/socket.h>
函数原型：int sendto(int sockfd,const void* msg,int len,unsigned int flags,const struct sockaddr* to,int tolen)
参数含义：
    sockfd：套接字描述符
    msg：指向要发送数据的指针
    len：数据长度
    flags：一般为0
    to：目标机的IP地址和端口号信息
    tolen：地址长度
返回值：
    成功：发送的字节数
    出错：-1

recvfrom
头文件：#include < sys/socket.h>
函数原型：int recvfrom(int sockfd,void * buf,int len,unsigned int flags,struct sockaddr* from,int* fromlen)
参数含义：
    sockfd：套接字描述符
    buf：存放接收数据的缓冲区
    len：数据长度
    flags：一般为0
    from：源机的IP地址和端口号信息
    tolen：地址长度
返回值：
    成功：接收的字节数
    出错：-1
````

### ntohl()、htonl()、ntohs()、htons()函数用法
ntohl()、htonl()、ntohs()、htons()这几个函数的作用是进行字节顺序的转换   
一、大端存储和小端存储  
1、大端存储：在计算机中，数据是按照字节存储的，多于一个字节的数据，把高字节部分存储在低地址，把低字节部分存储在高地址，这种方式称为大端存储。  
例：0x12345678这个数据，我们一般认为左边是高字节部分，右边是低字节部分，那么在采用大端存储的计算机内部的存储则为下面这样  
>低地址：     0x12(高字节)  
>xxxxx：     0x34  
>xxxxx：     0x56  
>高地址：     0x78(低字节)  
>即一个整型数据的首地址=高位部分（首地址=低地址）  
>典型使用大端存储的厂商是：Motorola  

2、小端存储：多于一个字节的数据，把高字节部分存储在高地址，把低字节部分存放在低地址。  
例：0x12345678这个数据，我们一般认为左边是高字节部分，右边是低字节部分，那么在采用小端存储的计算机内部的存储则为如何下这样  
>低地址：     0x78(低字节)
>xxxxx：     0x56
>xxxxx：     0x34
>高地址：     0x12(高字节)
>即首地址部分=低位部分（首地址=低地址）
>典型使用小端存储的厂商是：intel

二、网络传输模式  
网络传输通讯过程中，采用大端在前的方式进行数据发送，对于采用小端模式进行数据存储的系统，要把多字节的数据通过网络传输出去，则需要将数据的字节序进行转换，再进行发送。  
接收的过程亦然。  
在这几个函数中n代表net网络，h代表host主机，l代表long长整型，s代表short短整型。这几个函数的作用分别为：  
* ntohl()：将一个无符号长整形数从网络字节顺序转换为主机字节顺序
* htonl()：将一个无符号长整形数从主机字节顺序转换为网络字节顺序
* ntohs()：将一个无符号短整形数从网络字节顺序转换为主机字节顺序
* htons()：将一个无符号短整形数从主机字节顺序转换为网络字节顺序