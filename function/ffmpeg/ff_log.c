//
// Created by opayc on 2023/11/23.
//
/* log.c */
#include <stdio.h>
#include <string.h>

#include "../../include/ffmpeg/libavutil/log.h"

// 定义输出日志的函数，留白给使用者实现
extern void Ffmpeglog(int , char*);

static void log_callback(void *avcl, int level, const char *fmt, va_list vl)
{
    (void) avcl;
    char log[1024] = {0};
    int n = vsnprintf(log, 1024, fmt, vl);
    if (n > 0 && log[n - 1] == '\n')
        log[n - 1] = 0;
    if (strlen(log) == 0)
        return;
    Ffmpeglog(level, log);
}

void set_log_callback()
{
    // 给 av 解码器注册日志回调函数
    av_log_set_callback(log_callback);
}