package com.kuark.tool.advance.advance20201111.apollo;

import com.google.common.collect.*;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;

/**
 * @author rock
 * @detail
 * @date 2021/3/31 10:25
 */
public class LearnTwoMain {
    public static void main(String[] args) {
//        multimap();
        weakReference();

    }

    public static void weakReference(){
        //弱引用
        WeakReference wr=new WeakReference(new ReferBo());
        ReferBo b=(ReferBo)wr.get();
        b.show();
    }

    public static class ReferBo{
        private String name;

        public void show(){
            System.out.println("sssss");
        }
    }


    public static void partition(){
        //step.1 集合切割正常逻辑
        List<Integer> numList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        //把大集合分成指定大小的小集合
        List<List<Integer>> partList = Lists.partition(numList, 3);
        if (!CollectionUtils.isEmpty(partList)) {
            for (List<Integer> list : partList) {
                System.out.println(list.toString());
            }
        }
    }

    public static void multimap2(){
        //会对key按照排序规则进行排序的map集合，加上synchronizedSetMultimap是线程安全的集合
        Multimap deferredResults =Multimaps.synchronizedSetMultimap(TreeMultimap.create());
        //筛选出两个set集合的不同部分
        // Sets.difference()
    }

    public static void multimap(){
        //ArrayListMultimap 具有list和map的特性
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        //取key
        Collection<String> mykeys =myMultimap.keys();
        //去重
        mykeys= ImmutableSet.copyOf(mykeys);
        for (String mykey :mykeys ) {
            Collection<String> myvalue = myMultimap.get(mykey);
            //去重
            myvalue=ImmutableSet.copyOf(myvalue);
            System.out.println(myvalue);
        }
    }
}
