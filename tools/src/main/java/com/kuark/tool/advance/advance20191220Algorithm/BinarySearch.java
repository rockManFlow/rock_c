package com.kuark.tool.advance.advance20191220Algorithm;

/**
 * @author rock
 * @detail 二分查找算法实现
 * @date 2020/12/30 14:43
 */
public class BinarySearch {

    public static void main(String[] args) {
        Integer[] arrs=new Integer[]{2,5,7,8,10,11};
        binarySearch(arrs,0,arrs.length-1,1);
    }

    /**
     * 有序数组--二分查找算法
     * @param arrs
     * @param start
     * @param end
     * @param value
     */
    public static void binarySearch(Integer[] arrs,int start,int end,Integer value){
        System.out.println("start:"+start+"|end:"+end);
        if(start>end||arrs.length-1<end){
            System.err.println("数组不存在数值："+value);
            return;
        }
        int midIndex=(start+end)/2;
        if(arrs[midIndex]==value){
            System.out.println("数组存在："+value+"数组下标："+midIndex);
            return;
        }
        if(arrs[midIndex]>value){
            end=midIndex-1;
        }else {
            start=midIndex+1;
        }
        binarySearch(arrs,start,end,value);
    }
}
