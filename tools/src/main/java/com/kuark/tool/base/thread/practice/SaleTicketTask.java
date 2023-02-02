package com.kuark.tool.base.thread.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/21 15:29
 */
@Slf4j
public class SaleTicketTask implements Runnable {
    private volatile AtomicInteger tickets;
    private String functionName;
    private CountDownLatch latch;

    public SaleTicketTask(AtomicInteger tickets, String functionName, CountDownLatch latch) {
        this.tickets = tickets;
        this.functionName = functionName;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (tickets) {
                if (tickets.get() > 0) {
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("窗口={}，卖出第【{}】张票", functionName, tickets.decrementAndGet());
                    continue;
                }
                break;
            }
        }
        latch.countDown();
    }
}
