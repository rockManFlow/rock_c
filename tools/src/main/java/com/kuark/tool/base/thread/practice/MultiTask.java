package com.kuark.tool.base.thread.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/18 21:02
 */
@Slf4j
public class MultiTask implements Runnable {
    private CountDownLatch countDownLatch;
    private volatile AtomicLong num;
    public MultiTask(CountDownLatch countDownLatch,AtomicLong num){
        this.countDownLatch=countDownLatch;
        this.num=num;
    }
    @Override
    public void run() {
        log.info("Courrent Thread Name:{},num:{}",Thread.currentThread().getName()+Thread.currentThread().getId(),num);
        for(int i=0;i<100;i++){
            num.incrementAndGet();
        }
        countDownLatch.countDown();
    }
}
