package com.kuark.tool.base.thread.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/19 17:35
 */
@Slf4j
public class ResultTask implements Callable {
    private CountDownLatch countDownLatch;
    private volatile AtomicLong atomicLong;

    public ResultTask(CountDownLatch countDownLatch,AtomicLong atomicLong){
        this.countDownLatch=countDownLatch;
        this.atomicLong=atomicLong;
    }
    @Override
    public Object call() throws Exception {
        countDownLatch.await();
        log.info("result finish num:"+atomicLong.toString());
        return "ok";
    }
}
