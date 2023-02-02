package com.kuark.tool.base.threads;

import java.util.concurrent.*;

/**
 * 自定义线程池参数
 * Created by caoqingyuan on 2018/1/17.
 */
public class MyThreadPool {
    private static Executor pool;
    public static Executor getThreadPool(){
        if(pool==null){
            synchronized (MyThreadPool.class){
                if(pool==null){
                    //设置有界阻塞队列
                    LinkedBlockingQueue queue=new LinkedBlockingQueue<>(100000);
                    //用于创建新线程使用的工厂--就无需在手工编写对Thread的调用
                    ThreadFactory factory=new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r);
                            thread.setName("thread-pool-"+thread.getId());
                            return thread;
                        }
                    };
                    pool=new ThreadPoolExecutor(150, 150, 20, TimeUnit.SECONDS, queue,factory);
                }
            }
        }
        return pool;
    }
}
