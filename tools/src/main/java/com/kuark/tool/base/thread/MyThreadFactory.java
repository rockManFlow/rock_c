package com.kuark.tool.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/18 19:53
 */
public class MyThreadFactory extends AbstractThreadFactory {
    private final AtomicInteger threadNum=new AtomicInteger(0);
    private static String threadGroupName="Group";
    private String threadPrefix="thread-";
    private ThreadGroup threadGroup;
    private static String threadName="name";
    //是守护线程
    private boolean isDemon;
    private int priority;

    public MyThreadFactory(){
        this(new ThreadGroup(threadGroupName),threadName,false,Thread.NORM_PRIORITY);
    }
    public MyThreadFactory(String threadName){
        this(new ThreadGroup(threadGroupName),threadName,false,Thread.NORM_PRIORITY);
    }

    public MyThreadFactory(String threadName,int priority){
        this(new ThreadGroup(threadGroupName),threadName,false,priority);
    }

    public MyThreadFactory(String threadName,boolean isDemon){
        this(new ThreadGroup(threadGroupName),threadName,isDemon,Thread.NORM_PRIORITY);
    }
    private MyThreadFactory(ThreadGroup group,String threadName,boolean isDemon,int priority){
        this.threadGroup=group;
        this.threadName=threadName;
        this.isDemon=isDemon;
        this.priority=priority;
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread=new Thread(threadGroup,r,threadPrefix+threadNum.getAndIncrement()+"-"+this.threadName);
        thread.setDaemon(isDemon);
        return thread;
    }

    @Override
    public FutureTask newFutureTask(Callable callable) {
        return new FutureTask(callable);
    }
}
