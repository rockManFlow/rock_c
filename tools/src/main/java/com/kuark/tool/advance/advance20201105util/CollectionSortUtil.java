package com.kuark.tool.advance.advance20201105util;

import java.util.*;

/**
 * @author rock
 * @detail 这两种底层使用的是归并排序等排序算法
 * 所以，我们使用的一些工具也是使用的底层排序算法和底层的一些数据存储逻辑来实现上层看是华丽的功能
 * 要通过现象看本质
 * @date 2021/2/19 15:09
 */
public class CollectionSortUtil {
    public static void main(String[] args) throws ClassNotFoundException {
        List<String> list= Arrays.asList("6", "1", "3", "1","2");
        Collections.sort(list);
        for(String st:list){
            System.out.println(st);
        }

        Arrays.sort(list.toArray());

        //不可以重复，有序  set都是通过map实现去重
        /**
         * 不可以重复，有序  set都是通过map实现去重
         * 如何保证顺序：底层使用LinkedHashMap实现,使用的是节点类前后指针来保证顺序的
         * 一个数据节点保存着它的上一个节点和下一个节点信息
         */
        LinkedHashSet linkedHashSet=new LinkedHashSet(2);
        linkedHashSet.add(11);

        Class.forName("");

        ClassLoader loader=ClassLoader.getSystemClassLoader();
        loader.loadClass("");
    }
}
