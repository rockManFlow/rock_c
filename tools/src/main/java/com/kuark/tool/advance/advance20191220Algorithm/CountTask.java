package com.kuark.tool.advance.advance20191220Algorithm;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author rock
 * @detail 计数
 * @date 2019/12/20 16:55
 */
public class CountTask {

    public static void main(String[] srgs){
//        String st="djuhusadnjaYGDudsdnwu1457871hsg872ebhg154791214545122222";
//        System.out.println(letterCount(st));

        int[] nums=new int[]{9,10,1,1,8,8,2,2,3,1};
        disappearNum(nums);
    }

    /**
     * 字符串中各个字符出现的次数
     * @param st
     */
    public static Map<Character,Integer> letterCount(String st){
        Map<Character,Integer> countMap=new HashMap<>();
        if(StringUtils.isEmpty(st)){
            return countMap;
        }
        char[] charArray = st.toCharArray();
        for(Character ch:charArray){
            Integer count = countMap.get(ch);
            if(count==null){
                countMap.put(ch,1);
                continue;
            }
            countMap.put(ch,count+1);
        }
        return countMap;
    }

    /**
     * 给定一个整数数组，其中1≤a[i]≤n (n =数组大小)，有些元素出现两次，有些元素出现一次。
     * 找到[1,n]中不出现在这个数组中的所有元素。
     * 你能在O(n)运行时不需要额外的空间吗?您可以假定返回的列表不算作额外的空间。
     * @param nums
     */
    public static void disappearNum(int[] nums){
        Set<Integer> existNumSet=new HashSet<>();
        Set<Integer> disAppearNumSet=new HashSet<>();
        //O(n)=n
        Arrays.stream(nums).forEach(num->{
            existNumSet.add(num);
        });
        //O(n)=n
        for(int i=1;i<=nums.length;i++){
            if(!existNumSet.contains(i)){
                disAppearNumSet.add(i);
            }
        }
        System.out.println(disAppearNumSet);
    }
}
