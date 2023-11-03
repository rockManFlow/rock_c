//
// Created by opayc on 2023/10/31.
//

#ifndef ROCK_C_SORT_H
#define ROCK_C_SORT_H


#pragma once
#define  _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include<assert.h>
#include<time.h>
#include<string.h>


void PrintArray(int* arr,int n); //打印数组

void Swap(int* a, int* b); //交换数据

//默认全部实现升序排列
//插入排序
void InsertSort(int* arr,int n); //插入排序 : 稳定 最坏O( N^2 )  最好O( N )

void ShellSort(int* arr, int n); //希尔排序 : 不稳定 最坏O( N^2 )  最好O( N^1.3)


//选择排序
void SelectSort(int* arr, int n); //选择排序 : 不稳定 O( N^2 )

void HeapSort(int* arr, int n); //堆排序 : 稳定 O( N*logN )
void AdjustDown(int* arr, int n,int root); //堆排序所需要的向下调整接口


//交换排序
void BubbleSort(int* arr, int n); //冒泡排序 : 稳定 O(N^2)

void QuickSort(int* arr,int left,int right); //快排 : 不稳定 三数取中、区间非快排优化后O( N*LogN )  空间复杂度 O(logN)


//归并排序
void MergeSort(int* arr, int n);  //归并排序 : 稳定 O( N*logN ) 空间复杂度 O(N)

void MergeSortFile(const char* file); //对文件进行排序,适用于大量数据


//计数排序
void CountSort(int* arr, int n);

//----------------------------------------------------------------------------------
//以下为非递归快排所需要的栈头文件

typedef int StackData;
typedef struct Stack
{
    StackData* _a;  //存放数据
    int _top;       //顶标
    int _capacity;  //存放数量
}Sta;


void StackInit(Sta* pst);     //初始化堆栈

void StackDestroy(Sta* pst);  //销毁堆栈

void StackPush(Sta* pst, StackData x);  //插入数据

void StackPop(Sta* pst);  //删除数据

int StackSize(Sta* pst);  //堆栈数据个数
// int StackSize(Sta st);  不传入指针也可以,但为了接口函数的一致性传入指针也可以

int StackEmpty(Sta* pst);  //判断堆栈是否为空

StackData StackTop(Sta* pst);  //取出堆栈头的数据

#endif //ROCK_C_SORT_H
