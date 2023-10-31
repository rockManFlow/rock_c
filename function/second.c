//
// Created by opayc on 2023/10/31.
//
#include <stdio.h>

int length=0;
int moveAfterOne(int *a,int startIndex,int endIndex);

/**
 * 将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。
 * 即：先将序列的第1个记录看成是一个有序的子序列，
 * 然后从第2个记录逐个进行插入，直至整个序列有序为止。
 * 插入排序-升序-OK
 */
void insertSort(int *a){
    for(int j=1;j<length;j++){
        for(int i=j-1;i>=0;i--){
            if(a[i]<=a[j]){
                //从i之后到index-1都往后移动一位
                moveAfterOne(a,i+1,j);
                break;
            }
            if(a[i]>a[j]){
                if(i==0){
                    moveAfterOne(a,i,j);
                }
                continue;
            }
        }
    }
}

/**
 * 往后移动一位
 * @param a
 * @param startIndex
 * @param endIndex
 * @return
 */
int moveAfterOne(int *a,int startIndex,int endIndex){
    if(startIndex==endIndex){
        return 0;
    }
    if(startIndex>endIndex||startIndex<0||endIndex>length-1){
        printf("move fail:illegal param");
        return -1;
    }

    int v=a[endIndex];

    for(int i=endIndex-1;i>=startIndex;i--){
        a[i+1]=a[i];
    }
    a[startIndex]=v;

    return 0;
}


int main(){
    int a[]={8,10,2,11,5,3,2};
    length=(sizeof(a) / sizeof(int));
    printf("%d\n",length);
    insertSort(&a);
    for(int i=0;i<length;i++){
        printf("index:%d,value:%d\n",i,a[i]);
    }
}
