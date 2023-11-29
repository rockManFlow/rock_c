#设置cmake版本
cmake_minimum_required(VERSION 3.10)

#项目名字
project(rock_c)

#设置编译版本
set(CMAKE_CXX_STANDARD 10)

#声明头文件路径
set(INC_DIR ./include)
#声明链接库路径
set(LINK_DIR ./lib)

#引入头文件
include_directories(${INC_DIR})
#引入库文件
link_directories(${LINK_DIR})

#添加编译文件
add_executable(rock_c event-server.c hello-world.c bufferevent_client.c)

#将第三方库链接在一起
#target_link_libraries(rock_c libevent/libevent.a libevent/libevent_core.a libevent/libevent_extra.a libevent/libevent_pthreads.a)

target_link_libraries(rock_c
        #FFmpeg 库
        avcodec
        avfilter
        avformat
        avutil
        swresample
        swscale
        )