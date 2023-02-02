package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rock
 * @detail
 * @date 2019/9/16 17:24
 */
@Slf4j
public class SaleTicketTaskA implements Runnable{
    //总票数，使内存可见
    private volatile AtomicInteger sum;

    private final static String lock="LOCK";
    public SaleTicketTaskA(AtomicInteger sum){
        this.sum=sum;
    }


    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        while (true) {
          synchronized (lock) {
            if (sum.get()>0) {
              sum.decrementAndGet();
              log.info(
                  "Sale ThreadName=[{}],TicketNum=[{}]",
                  currentThread.getName(),
                      sum);
            }else{
                break;
            }
          }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                log.error("中断异常",e);
            }
        }
        log.info(
                "Sale ThreadName=[{}],end",
                currentThread.getName());
    }
}
