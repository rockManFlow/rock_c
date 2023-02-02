package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2020/5/5 14:09
 */
public class CountDownTask2 implements Callable<String> {
    private CountDownLatch latch;
    private String name;
    CountDownTask2(String name, CountDownLatch latch){
        this.name=name;
        this.latch=latch;
    }
    @Override
    public String call() throws Exception {
        System.out.println("sleep:"+name);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("run:"+name);
        latch.countDown();
        return "OK"+name;
    }
}
