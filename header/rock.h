//
// Created by opayc on 2023/10/25.
//

#ifndef ROCK_C_ROCK_H
#define ROCK_C_ROCK_H

//定义全局常量
#define GLOBAL_CONSTANT_NUM_1 100
#define GLOBAL_CONSTANT_STR_1 "xiaohong"

//在header中声明方法名
void show();
int write(int a,char *c);

void printTypeSize();

//声明全局变量
int process=10;
char *str;

struct base_info{
    char *addr;
    unsigned int age;
    char *phone;
};
#endif //ROCK_C_ROCK_H
