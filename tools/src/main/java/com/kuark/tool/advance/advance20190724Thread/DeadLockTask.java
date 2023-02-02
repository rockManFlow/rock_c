package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail
 * @date 2019/9/16 21:00
 */
@Slf4j
public class DeadLockTask implements Runnable {
    private Object lock1;
    private Object lock2;

    public DeadLockTask(Object lock1,Object lock2){
        this.lock1=lock1;
        this.lock2=lock2;
    }
    @Override
    public void run() {
        log.info("ThreadId="+Thread.currentThread().getId());
        synchronized (lock1){
            log.info("ThreadId="+Thread.currentThread().getId()+"|Get 1");
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                log.info("ThreadId="+Thread.currentThread().getId()+"|Get 2");
            }
        }
    }
}
