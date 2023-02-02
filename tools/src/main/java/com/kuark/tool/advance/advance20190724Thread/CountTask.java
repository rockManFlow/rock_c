package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/24 19:17
 */
public class CountTask implements Runnable {
    //必须保证对象可见，才能线程安全
    private volatile AtomicLong count;
    private CountDownLatch latch;

    CountTask(AtomicLong count, CountDownLatch latch){
        this.count=count;
        this.latch=latch;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            count.incrementAndGet();
        }
        latch.countDown();
    }
}
