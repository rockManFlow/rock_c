package com.kuark.tool.base.thread.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/20 10:45
 */
@Slf4j
public class PriorityTask implements Runnable {
    //记录当前线程执行次数
    private AtomicLong runCount=new AtomicLong(0);
    private int priority;
    public PriorityTask(int priority){
        this.priority=priority;
    }
    @Override
    public void run() {
        //设置当前线程优先级
        Thread.currentThread().setPriority(this.priority);
        log.info("Conrrent Thread Info ThreadName:{},id:{},runCount:{},priority:{}",Thread.currentThread().getName(),Thread.currentThread().getId(),runCount.addAndGet(1),Thread.currentThread().getPriority());
    }
}
