package com.kuark.tool.advance.advance20201111.rocketmq;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @author rock
 * @detail
 * @date 2021/4/6 10:30
 */
public class LearnOneMain {
    public static void main(String[] args) throws InterruptedException {
//        String st="1#2#3";
//        boolean contains = st.contains("21");
//        System.out.println(contains);
//
//        LocalDateTime localDateTime = LocalDateTime.now().minusMonths(1).withHour(23).withMinute(59).withSecond(59);
//        LocalDateTime with = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println("before mouth="+with);
//
//        LocalDateTime localDateTime2 = LocalDateTime.now().minusMonths(3).withHour(0).withMinute(0).withSecond(0);
//        LocalDateTime with2 = localDateTime2.with(TemporalAdjusters.firstDayOfMonth());
//        System.out.println("3 mouth="+with2);

//        spliter();

//        List<Integer> list=new ArrayList<>();
//        list.add(1);
//        list.add(4);
//        list.add(6);
//
//        int index=ThreadLocalRandom.current().nextInt(list.size());
//        System.out.println(index);
//        Integer integer = list.get(index);
//        System.out.println(integer);

        List<Integer> list2=new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(3);
        list2.add(4);
        Map<Integer, List<Integer>> map1 = list2.stream().collect(Collectors.groupingBy(r -> r));
        System.out.println("map1:"+JSON.toJSONString(map1));

        List<Integer> list3=new ArrayList<>();
        list3.add(2);
        list3.add(3);
        list3.add(5);
        list3.add(4);
        list3.add(4);
        list3.add(4);
        list3.add(4);
        Map<Integer, List<Integer>> map2 = list3.stream().collect(Collectors.groupingBy(r -> r));
        System.out.println("map2:"+JSON.toJSONString(map2));
        List<Integer> re=new ArrayList<>();
        map2.keySet().stream().forEach(m->{
            List<Integer> list = map2.get(m);
            List<Integer> list1 = map1.get(m);
            if(CollectionUtils.isEmpty(list1)){
                re.addAll(list);
            }else {
                int a=list.size()-list1.size();
                if(a>0){
                    for(int i=0;i<a;i++){
                        re.add(m);
                    }
                }
            }
        });

        System.out.println(re);
    }

    public static void lock() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
        //reentrantReadWriteLock统一管理读写锁
        reentrantReadWriteLock.readLock().lockInterruptibly();
    }

    public static void listAdd(){
        List<String> list=new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add(0,"3333");
        System.out.println(JSON.toJSONString(list));
    }

    public static void spliter(){
        String picUrls="swde123dede|11111111";
        Splitter splitter=Splitter.on("|");
        List<String> list = splitter.splitToList(picUrls);
        System.out.println(list);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CardConfig{
        private String title;
        private String text;
        private String picUrl;
        private Integer color;
        private String jumpUrl;
        private String cardId;
        private Integer sort;
    }
}
