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

#指定生成文件的输出路径 /Users/opayc/products/rock_c/output
set(PROJECT_BINARY_DIR D:/myProjects/rock_c/output)
SET(EXECUTABLE_OUTPUT_PATH ${PROJECT_BINARY_DIR}/bin)
SET(LIBRARY_OUTPUT_PATH ${PROJECT_BINARY_DIR}/lib)


#引入头文件
include_directories(${INC_DIR}/ffmpeg/win)
#引入库文件
link_directories(${LINK_DIR}/ffmpeg/win)




#添加编译文件
add_executable(rock_c function/ffmpeg/my.c)

#将第三方库链接在一起
target_link_libraries(rock_c avcodec avdevice
        avfilter
        avformat
        avutil
        swresample
        swscale
        )