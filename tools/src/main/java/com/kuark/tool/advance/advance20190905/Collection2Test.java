package com.kuark.tool.advance.advance20190905;

import com.kuark.tool.advance.advance20190905.vo.ComparableVo;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @author rock
 * @detail
 * @date 2021/8/9 15:25
 */
public class Collection2Test {
    public static void main(String[] args) {
//        Integer n=4;
//        Integer data=5;
//        Integer s1=(n-1)&data;
//        System.out.println("s1="+s1);
//        n=5;
//        Integer s2=(n-1)&data;
//        System.out.println("s2="+s2);
//        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
//        linkedHashMap.put("111","aaa");
//        linkedHashMap.put("222","bbb");
//        linkedHashMap.put("333","ccc");
//        linkedHashMap.put("444","ddd");
//        //插入顺序及遍历顺序
//        for(Map.Entry<String,String> kv:linkedHashMap.entrySet()){
//            System.out.println("key:"+kv.getKey()+"|value:"+kv.getValue());
//        }
//
//        TreeMap<String,String> treeMap=new TreeMap<String,String>();
//        treeMap.put("777","ddd");
//        treeMap.put("222","bbb");
//        treeMap.put("111","aaa");
//        treeMap.put("555","ccc");
//        SortedMap<String, String> sortedMap = treeMap.tailMap("333");
//        System.out.println(sortedMap);
//        ComparableVo v=new ComparableVo();
//        treeMap.put(v,"sss");
        //会对key进行排序,类型必须实现Comparable接口,如未实现会报未实现异常ClassCastException
//        for(Map.Entry<String,String> kv:linkedHashMap.entrySet()){
//            System.out.println("treeMap key:"+kv.getKey()+"|value:"+kv.getValue());
//        }

        //TreeMap本身提供了一个tailMap(K fromKey)方法，支持从红黑树中查找比fromKey大的值的集合，但并不需要遍历整个数据结构。
//        SortedMap<String,String> sortedMap = treeMap.tailMap("444");
//        Set<String> set = sortedMap.keySet();
//        System.out.println(set);
//
//        HashMap hashMap=new HashMap(10);
//        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
//        concurrentHashMap.put();
//        concurrentHashMap.get()
        //同步队列
//        SynchronousQueue synchronousQueue=new SynchronousQueue();
        concurrentHashMap();
    }

    public static void concurrentHashMap(){
        //这两个方法都是原子操作
        ConcurrentHashMap map=new ConcurrentHashMap(2);
        map.put("111",111);

        //不存在，则赋值，返回赋值。存在，返回已存在的值
        Object o = map.computeIfAbsent("222", key -> {
            System.out.println(key);
            return 15;
        });
        System.out.println("k1:"+o);

        //不存在，则赋值，返回null。存在，返回存在的值
        Object o1 = map.putIfAbsent("333", 333);
        System.out.println("k2:"+o1);
        System.out.println(map);
    }
}
