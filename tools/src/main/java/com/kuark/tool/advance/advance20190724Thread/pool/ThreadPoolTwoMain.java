package com.kuark.tool.advance.advance20190724Thread.pool;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class ThreadPoolTwoMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t = new Thread(r);
                //添加指定线程名
                t.setName("Test-Pool-" + t.getId());
                return t;
            }
        });

//        threadPool.execute();

        monitorPool(threadPool);

        //使用一个计数器跟踪完成的任务数
        AtomicInteger atomicInteger = new AtomicInteger();

        //每隔1秒提交一次，一共提交20次任务
        IntStream.rangeClosed(1, 20).forEach(i -> {

            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            int id = atomicInteger.incrementAndGet();

            try {
                threadPool.submit(() -> {
                    log.info("{} started", id);

                    //每个任务耗时10秒
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {

                    }
                    log.info("{} finished", id);
                });

            } catch (Exception ex) {
                //提交出现异常的话，打印出错信息并为计数器减一
                log.error("error submitting task {}", id, ex);
                atomicInteger.decrementAndGet();

            }

        });

        TimeUnit.SECONDS.sleep(60);
    }

    private static void monitorPool(ThreadPoolExecutor threadPool){
        //如何监控线程池中可用线程数、积压任务数、完成任务数等信息
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("Pool Size: {}", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("=========================");

        }, 0, 1, TimeUnit.SECONDS);
    }
}
