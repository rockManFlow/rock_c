package com.kuark.tool.base.thread.practice;

import com.kuark.tool.base.thread.MyThreadFactory;
import com.kuark.tool.base.thread.MyThreadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/18 20:55
 */
public class TestMain {
    private static Integer startNum=new Integer(0);
    public static void resultSum(){
        //1、多个线程同时执行任务，最终进行汇总结果
        //各个10个子线程分别对一个成员变量进行加100次，最后汇总结果为1000
        //主要的问题点是多线程之间内存变量的可见性
        AtomicLong num=new AtomicLong(startNum);
        MyThreadFactory factory=new MyThreadFactory("resultSum");
        CountDownLatch countDownLatch=new CountDownLatch(10);
        ThreadPoolExecutor pool=new MyThreadPool(10,10).buildThreadPool(factory);
        for(int i=0;i<10;i++) {
            pool.execute(factory.newThread(new MultiTask(countDownLatch,num)));
        }
        pool.submit(new ResultTask(countDownLatch,num));
    }

    public static void threadInterrupt() throws InterruptedException {
        MyThreadFactory factory=new MyThreadFactory("interrupt");
        Integer aa=11;
        Thread a=factory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start run");
                synchronized (aa) {
                    try {
                        aa.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("end run");
            }
        });
        a.start();
        a.interrupt();

        Thread b=factory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("bstart run");
                synchronized (aa) {
                    try {
                        aa.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("bend run");
            }
        });
        b.start();


        Thread.sleep(1000);
        System.out.println("min");
        a.interrupt();

    }

    public static void priority(){
        //多线程优先级问题，及一个线程执行任务多，另外线程执行任务少
        //如何设置线程池中线程优先级
        MyThreadFactory factory=new MyThreadFactory("priority");
        ThreadPoolExecutor pool=new MyThreadPool(10,10).buildThreadPool(factory);

        for(int i=1;i<=10;i++){
            Thread thread = factory.newThread(new PriorityTask(i));
            pool.execute(thread);
        }
    }

    /**
     * 多个线程同时卖票，最后进行汇总结束
     * @throws InterruptedException
     */
    public static void saleTicket() throws InterruptedException {
        ThreadPoolExecutor pool=new MyThreadPool(10,10).buildThreadPool(new MyThreadFactory("saleTicket"));

        CountDownLatch latch=new CountDownLatch(10);
        AtomicInteger ticketNum=new AtomicInteger(100);
        for(int i=0;i<10;i++){
            pool.execute(new SaleTicketTask(ticketNum,"CK"+i,latch));
        }
        latch.await();
        pool.shutdown();
        System.out.println("pool run status "+pool.isShutdown());
    }

    /**
     * 来统计各个线程执行多少次？
     */
    public static void runCount(){
        
    }

    public static void main(String[] args) throws InterruptedException {
//        threadInterrupt();
//        priority();
        saleTicket();
        System.out.println("main end");
    }
}
