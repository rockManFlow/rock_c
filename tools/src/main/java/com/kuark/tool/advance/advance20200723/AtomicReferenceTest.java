package com.kuark.tool.advance.advance20200723;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author rock
 * @detail
 * @date 2020/8/21 10:30
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        //使对象可以进行原子操作
        AtomicReference<Integer> num=new AtomicReference(10);
    }
}
