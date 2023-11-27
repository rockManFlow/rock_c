//
// Created by opayc on 2023/11/23.
//
#include <stdio.h>
#include "../../include/ffmpeg/libavutil/log.h"

/**
 * 具体日志输出实现
 * @param l
 * @param t
 */
void Ffmpeglog(int l, char* t) {
    //打印info级别及以上的日志
    if(l <= AV_LOG_INFO)
        fprintf(stdout, "%s\n", t);
}