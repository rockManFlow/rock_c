package com.kuark.tool.advance.advance20191220Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rock
 * @detail
 * @date 2020/1/14 16:51
 */
public class SortingAlgorithm {

    public static void main(String[] args){
        List<Integer> input=new ArrayList<>();
        input.add(10);
        input.add(8);
        input.add(12);
        input.add(4);
        input.add(2);
        input.add(9);
        input.add(7);
        input.add(3);
        input.add(5);
        Integer[] arrays=new Integer[input.size()];
        Integer[] integers = mergeSort(input.toArray(arrays));
        Arrays.stream(integers).forEach(num->{
            System.out.println(num);
        });
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(List<Integer> input){
        if(input==null||input.isEmpty()){
            return;
        }
        Integer countNum=input.size()-1;
        Integer startLocal=0;
        for(int j=1;j<=countNum;j++){
            for(int i=startLocal;i<countNum;i++){
                if(input.get(i)>input.get(i+1)){
                    Integer middleNum=input.get(i);
                    input.set(i,input.get(i+1));
                    input.set(i+1,middleNum);
                }
            }
        }
        System.out.println("冒泡排序："+input);
    }

    /**
     * 选择排序
     */
    public static void selectSort(List<Integer> input){
        Integer minLocal=0;
        for(int i=0;i<input.size()-1;i++){
            Integer startLocal=i;
            for(int j=startLocal;j<input.size();j++){
                if(input.get(minLocal)>input.get(j)){
                    minLocal=j;
                }
            }
            Integer currentNum=input.get(startLocal);
            input.set(startLocal,input.get(minLocal));
            input.set(minLocal,currentNum);
        }
        System.out.println("选择排序："+input);
    }

    /**
     * 插入排序
     */
    public static void insertSort(List<Integer> input){
        if(input==null||input.isEmpty()){
            return;
        }
        Integer finishLocal=0;
        for(int i=1;i<input.size();i++){
            Integer nowNum=input.get(i);
            for(int j=finishLocal;j>=0;j--){
                if(input.get(j)>nowNum){
                    Integer middleNum=input.get(j);
                    input.set(j,nowNum);
                    input.set(j+1,middleNum);
                }else{
                    break;
                }
            }
            finishLocal+=1;
        }
        System.out.println("插入排序："+input);
    }

    /**
     * 归并排序
     */
    public static Integer[] mergeSort(Integer[] input){
        if (input.length < 2) return input;
        int mid=input.length/2;
        Integer[] first= Arrays.copyOfRange(input,0,mid);
        Integer[] end=Arrays.copyOfRange(input,mid,input.length);
        return merge(mergeSort(first),mergeSort(end));
    }

    private static Integer[] merge(Integer[] first,Integer[] end){
        Integer[] mergeArray=new Integer[first.length+end.length];
        for(int index=0,i=0,j=0;index<mergeArray.length;index++){
            if(i>=first.length){
                mergeArray[index]=end[j++];
            }else if(j>=end.length){
                mergeArray[index]=first[i++];
            }else if(first[i]>end[j]){
                mergeArray[index]=end[j++];
            }else{
                mergeArray[index]=first[i++];
            }
        }
        return mergeArray;
    }

    public static void quitSort(){

    }
}
