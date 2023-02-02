package com.kuark.tool.advance.advance20190724Thread.pool;

import com.kuark.tool.model.concurrents.test.MyThread;

import java.util.concurrent.*;

/**
 * @author rock
 * @detail
 * @date 2021/5/27 11:49
 */
public class ThreadPoolOneMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 对于线程池中使用的队列
         * SynchronousQueue：直接提交任务。队列中不保存
         * 例如 LinkedBlockingQueue()：无界队列，最大线程数始终为核心线程数
         * 例如 ArrayBlockingQueue()：有界队列
         */

        issuePool();
    }

    private static void cachePool(){
        //newCachedThreadPool(可缓存的线程池 无限线程)
        ExecutorService pool=Executors.newCachedThreadPool();
        /**
         * 实现源码
         * new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
         * 核心线程0，最大线程是最大，所以当有新任务来的时候，如果没有空闲线程时，会一直创建线程来执行任务。
         * 当线程超过60s无任务执行，会杀死该空闲线程(keepAliveTime:当线程数大于corePoolSize时，此为终止空闲线程等待新任务的最长时间) 0
         * 使用同步队列，每放进一个任务，必须同时有一个线程来实时取并执行。否则就会阻塞
         */
    }

    private static void signalPool(){
        //无界队列  单线程池
        ExecutorService pool= Executors.newSingleThreadExecutor();//创建一个单线程池
        for(int i=0;i<100;i++){
            pool.submit(new MyThread());
        }
        pool.shutdown();

        ExecutorService pool2 = Executors.newFixedThreadPool(5);// 创建一个固定大小为5的线程池

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);//定时线程池
//        scheduledExecutorService.scheduleAtFixedRate()
        /**
         * 定时线程池实现依赖于延迟队列 自定义实现的一个特殊的延迟队列 DelayedWorkQueue
         * 延迟线程池具体原理还没懂
         */
    }

    private static void issuePool() throws ExecutionException, InterruptedException {
        /**
         * 捕获线程异常方式：实现UncaughtExceptionHandler
         */
        Thread t=new Thread(()->{
            System.out.println("Thread run");
            throw new RuntimeException("yichangbuhuo");
        });
        t.setUncaughtExceptionHandler(new MtUncaughtExceptionHandler());
        t.start();

        /**
         * 线程池异常捕获
         * ThreadPoolExecutorMonitor
         */
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Object> future = pool.submit(() -> {
            System.out.println("run issue");
            throw new RuntimeException("test error");
        });
        //如果没有这个获取结果的操作，异常是获取不到的
        future.get();
    }
}
