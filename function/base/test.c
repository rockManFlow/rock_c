//
// Created by Dell on 2024/2/18.
//
#include <stdio.h>
void swap(int *a,int *b){
    //int *a=&a等价于int a;所以，*a等价于a,下面*a=*b;这个才成立
    printf("swap value %d\n",*a);
    int temp=*a;
    *a=*b;
    *b=temp;
}

int main(){
    int arr[5]={1,2,3,4,5};
    int *ptr=arr;
    for(int i=0;i<5;i++){
        printf("1 value %d\n",*ptr);
        ptr++;//地址位置+1
    }
    ptr=arr;
    swap(&ptr[0],&ptr[4]);
    for(int i=0;i<5;i++){
        printf("2 value %d\n",*ptr);
        ptr++;//地址位置+1
    }
    return 0;
}
