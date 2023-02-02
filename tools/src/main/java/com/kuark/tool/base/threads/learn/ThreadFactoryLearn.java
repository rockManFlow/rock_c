package com.kuark.tool.base.threads.learn;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caoqingyuan
 * @detail 线程工厂
 * @date 2018/5/17 15:27
 */
public class ThreadFactoryLearn  implements ThreadFactory{
    private final AtomicInteger threadNum=new AtomicInteger(0);
    private String prefix;
    private boolean deamo;
    private ThreadGroup threadGroup;

    public ThreadFactoryLearn(String prefix,boolean deamo){
        this.deamo=deamo;
        this.prefix=prefix+"-thread-"+threadNum.getAndIncrement();
        SecurityManager s = System.getSecurityManager();
        this.threadGroup=(s==null)?Thread.currentThread().getThreadGroup():s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread=new Thread(this.threadGroup,r,this.prefix,0);
        thread.setDaemon(deamo);
        return thread;
    }

    public Thread newThread() {
        Thread thread=new Thread(this.threadGroup,this.prefix);
        thread.setDaemon(deamo);
        return thread;
    }
}
