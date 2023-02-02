package com.kuark.tool.advance.advance20191220Algorithm;

/**
 * @author rock
 * @detail 冒泡排序 时间复杂度为O(n方)
 * @date 2021/1/26 17:57
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] arr=new Integer[]{9,10,5,12,6,2,1,16,8,7};
        sort(arr);
        for(Integer num:arr){
            System.out.println("num="+num);
        }
    }

    public static void sort(Integer[] arr){
        for(int j=0;j<arr.length-1;j++){
            oneSort(arr,arr.length-j);
        }
    }

    public static void oneSort(Integer[] arr,Integer sum){
        for(int i=0;i<sum-1;i++){
            Integer pre=arr[i];
            Integer fix=arr[i+1];
            if(pre>fix){
                arr[i+1]=pre;
                arr[i]=fix;
            }
        }
    }
}
