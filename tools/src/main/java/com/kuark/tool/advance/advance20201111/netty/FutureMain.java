package com.kuark.tool.advance.advance20201111.netty;

import java.util.concurrent.*;

/**
 * @author rock
 * @detail
 * @date 2021/3/16 10:34
 */
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //表示有异步结果返回的任务
        /**
         * Future 表示异步计算的结果,包含对异步线程的操作方法：取消（线程中断实现）、线程执行状态、获取线程结果
         *
         * FutureTask是对Future的一个实现
         * FutureTask会把线程的执行结果(可能是结果，也可能是异常)放到outcome中，
         * 这是个普通的对象，如何来保证线程安全的呢？
         * 通过线程状态来保证的，线程状态的更新时CAS线程安全的，这个值的读取只能是终态之后才会被读取，
         * 并且终态之后是不允许更改该值的。（及读写是通过状态分开的）
         *
         * FutureTask是如何把线程执行结果更新的
         * 对Running的run方法进一步进行了封装，当线程run执行完成之后，FutureTask的run会赋值返回结果
         * 包装的结果
         */
        FutureTask future=new FutureTask(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run");
            }
        },"ok");
        new Thread(future).start();
        boolean done = future.isDone();
        System.out.println("done:"+done);
        TimeUnit.SECONDS.sleep(2);
        Object o = future.get();
        System.out.println("result:"+o);

//        Future future1=new FutureTask(new Callable() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        });



    }

    private static void executorFuture() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        Future<String> futureStr = scheduledExecutorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        }, "ok");
        String s = futureStr.get();
        System.out.println("future result:"+s);
    }
}
