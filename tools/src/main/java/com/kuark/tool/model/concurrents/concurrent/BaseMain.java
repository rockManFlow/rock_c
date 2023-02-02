package com.kuark.tool.model.concurrents.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by caoqingyuan on 2017/5/8.
 */
public class BaseMain {
    public static final ScheduledThreadPoolExecutor pool=new ScheduledThreadPoolExecutor(20);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        String result="11";
//        RunnableFuture future=new FutureTask(new RunnableImpl(),result);
//        future.run();
//        String o = (String)future.get();//执行完该线程之后会返回指定的结果
//        System.out.println("baseMain "+o);
        for(int i=0;i<20;i++){
            System.out.println("main i="+i);
            pool.submit(new RunnableImpl(i));
        }
    }
}
