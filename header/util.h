//
// Created by opayc on 2023/11/15.
//

#ifndef ROCK_C_UTIL_H
#define ROCK_C_UTIL_H

int full_write(int fd,void *buffer,int length);

/**
 * 普通的读
 * @param fd
 * @param buffer
 * @param length
 * @return
 */
int full_read(int fd,void *buffer,int length);
/**
 * 读指定长度的数据，不然不会返回，除非出错
 * @param fd
 * @param buffer
 * @param length
 * @return
 */
int full_read_size(int fd,void *buffer,int length);

#endif //ROCK_C_UTIL_H
