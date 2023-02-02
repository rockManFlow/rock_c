package com.kuark.tool.advance.advance20210810;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author rock
 * @detail
 * @date 2022/1/17 14:47
 */
@Slf4j
public class TestMain2 {
    public static void main(String[] args) throws ClassNotFoundException {
//        ConcurrentHashMap map=new ConcurrentHashMap();
//        Class.forName("");
//        ConcurrentHashMap<String,LongAdder> map=new ConcurrentHashMap(2);
////        for(int i=0;i<2;i++){
////            map.computeIfAbsent("key",k-> {
////                System.out.println(k);
////                return new LongAdder();
////            }).increment();
////        }
////        System.out.println(map.get("key").longValue());

        Long aLong = Long.valueOf("1048576");
        System.out.println(aLong);
    }


}
