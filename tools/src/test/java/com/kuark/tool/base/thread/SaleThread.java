package com.kuark.tool.base.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caoqingyuan
 * @detail 卖票线程
 * @date 2019/5/15 18:25
 */
public class SaleThread implements Runnable {
    AtomicInteger sumTicket;
    public SaleThread(AtomicInteger sumTicket){
        this.sumTicket=sumTicket;
    }
    @Override
    public void run() {
        //局部变量是线程私有的
        while (sumTicket.intValue()>0){
            System.out.println(Thread.currentThread().getName()+"|卖出第"+sumTicket.getAndDecrement()+"票");
        }
    }

    public static void main(String[] args){
        AtomicInteger sumTicket=new AtomicInteger(100);
        SaleThread saleThread=new SaleThread(sumTicket);
        Thread t1=new Thread(saleThread,"窗口1");
        Thread t2=new Thread(saleThread,"窗口2");
        t1.start();
        t2.start();
    }
}
