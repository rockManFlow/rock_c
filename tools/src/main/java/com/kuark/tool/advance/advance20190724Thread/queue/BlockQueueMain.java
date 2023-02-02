package com.kuark.tool.advance.advance20190724Thread.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2021/2/5 14:42
 */
public class BlockQueueMain {
    public static void main(String[] args) throws InterruptedException {
//        BlockQueue<Integer> queue=new BlockQueue<Integer>(3);
        BlockQueueUpgrade<Integer> queue=new BlockQueueUpgrade<Integer>(3);

        ExecutorService provider = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final Integer num=i;
            provider.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("provider:"+Thread.currentThread().getId()+" add");
                        queue.add(num);
                        System.out.println("provider:"+Thread.currentThread().getId()+" add num:"+num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        TimeUnit.SECONDS.sleep(10);

        ExecutorService consumer = Executors.newCachedThreadPool();
        for(int i=0;i<11;i++){
            consumer.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("consumer:"+Thread.currentThread().getId()+" get");
                        Integer num = queue.get();
                        System.out.println("consumer:"+Thread.currentThread().getId()+" get num:"+num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
