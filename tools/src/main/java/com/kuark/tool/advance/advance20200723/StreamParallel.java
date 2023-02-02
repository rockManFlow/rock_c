package com.kuark.tool.advance.advance20200723;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rock
 * @detail todo 并发流的实现
 * @date 2020/7/27 14:34
 */
public class StreamParallel {

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>(5);
        list.add(1);
        list.add(2);list.add(3);list.add(4);list.add(5);
        //这是并行执行，最后统计结果---多线程执行
        List<Integer> collect = list.stream().parallel().map(num -> {
            System.out.println("num:" + num);
            return num + 1;
        }).collect(Collectors.toList());
        System.out.println("sum:"+collect);
    }
}
