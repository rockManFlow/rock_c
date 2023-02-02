package com.kuark.tool.advance.advance20190905;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author rock
 * @detail ArrayList问题分析
 * @date 2019/11/20 16:30
 */
public class ArrayListTypeMain {
    public static void main(String[] args){
        delList();
    }

    /**
     * 集合问题
     */
    public static void delList(){
        /**
         * todo 这种的为什么不可以删除？
         * List<Integer> numList2= Arrays.asList(2,1,19,13);
         *         numList2.remove(2);
         * 通过Arrays方式来创建的集合，是没法进行删除及添加元素的，看源码可知，
         * 返回的集合类是Arrays自己内部定义的静态内部类，这个类中没有删除及添加方法的实现，所以，不可以
         * 但可修改
         */
        List<Integer> numList= new ArrayList<Integer>(2);
        numList.add(2);
        numList.add(19);
        numList.add(13);
        /**
         * todo 这种方式删除集合元素是不允许的，原因已知
            for(Integer num:numList){
                if(num==19){
                    numList.remove(num);
                }
            }
         */

        /**
         * todo 以这种方式删除
         */
        Iterator<Integer> iterator = numList.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next==19){
                iterator.remove();
            }
        }
        System.out.println(numList);
    }
}
