package com.kuark.tool.base.thread;

import java.util.concurrent.*;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/18 20:23
 */
public class MyThreadPool {
    private int corePoolSize=5;
    private int maximumPoolSize=5;
    public MyThreadPool(int corePoolSize,int maximumPoolSize){
        this.corePoolSize=corePoolSize;
        this.maximumPoolSize=maximumPoolSize;
    }

    public ThreadPoolExecutor buildThreadPool(ThreadFactory factory){
        return buildThreadPool(corePoolSize,maximumPoolSize,5000,new LinkedBlockingQueue(200),factory
        ,new MyRejectedExecutionHandler());
    }

    private ThreadPoolExecutor buildThreadPool(int corePoolSize, int maximumPoolSize
    , long keepAliveTime, BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory
    ,RejectedExecutionHandler handler){
        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.MILLISECONDS,workQueue,threadFactory,handler);
    }
}
