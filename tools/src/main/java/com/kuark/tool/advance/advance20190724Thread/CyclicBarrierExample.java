package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author rock
 * @detail
 * @date 2019/9/17 10:38
 */
@Slf4j
public class CyclicBarrierExample {
    private ExecutorService executorService;
    private CyclicBarrier cyclicBarrier;
    private int parties;

    public CyclicBarrierExample(int parties) {
        executorService = Executors.newFixedThreadPool(parties);
        //阻塞/同步线程的个数  当阻塞存在时执行的命令
        cyclicBarrier = new CyclicBarrier(parties, () -> log.info(Thread.currentThread().getName() + " gets barrierCommand done"));
        this.parties = parties;
    }

    public static void main(String[] args) {
        CyclicBarrierExample cyclicBarrierExample = new CyclicBarrierExample(10);
        cyclicBarrierExample.example();
    }

    public void example() {
        for (int i = 0; i < parties; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(2000);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread().getName() + " gets job done");
            });
        }
        executorService.shutdown();
    }
}
