package com.kuark.tool.advance.advance20190724Thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail 覆盖 beforeExecute、afterExecute、terminated方法，达到对线程行为的控制和监控
 * 可以用于线程中任务的开始和结束的监控，当线程终止的时候会执行该方法terminated
 * @date 2021/2/5 11:05
 */
public class ThreadPoolExecutorMonitor extends ThreadPoolExecutor {
    public ThreadPoolExecutorMonitor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * 线程中任务执行之前会执行
     * @param t
     * @param r
     */
    @Override
    public void beforeExecute(Thread t, Runnable r){
        System.out.println("before execute");
    }

    /**
     * 任务执行完成之后会执行，通过覆盖这个方法可以捕获线程池异常
     * @param r
     * @param t
     */
    @Override
    public void afterExecute(Runnable r, Throwable t){
        System.out.println("after execute");
    }

    /**
     * 本线程终止的时候才会执行该方法
     */
    @Override
    public void terminated(){
        System.out.println("thread terminated");
    }

    public static void main(String[] args) {
        ThreadPoolExecutorMonitor pool=new ThreadPoolExecutorMonitor(2, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(5));
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("run start");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run end");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("run start2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run end2");
            }
        });
    }
}
