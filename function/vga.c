//
// Created by opayc on 2023/10/27.
//音视频处理器
//
#include <event.h>
struct event_base* base;
int main(){
    /**
     * ffmpeg的使用在C中
     * 引入三方项目方式
     */
    struct event listen_ev;

    base = event_base_new();
//    event_set(&listen_ev, sock, EV_READ|EV_PERSIST, on_accept, NULL);
    event_base_set(base, &listen_ev);
    event_add(&listen_ev, NULL);
    event_base_dispatch(base);
}