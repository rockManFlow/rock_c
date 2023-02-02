package com.kuark.tool.advance.advance20190724Thread;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail 停止线程方式
 * @date 2022/2/11 15:44
 */
public class InterruptMain {
//    public static void main(String[] args) throws InterruptedException {
//        interruptDoubleStop();
//        System.out.println("main end");
//    }
    public static void main(String[] args) {
        ArrayList<String> list = com.google.common.collect.Lists.newArrayList("bill,transfer,transaction,cashier".split(","));
        System.out.println(list.contains("transfer"));
    }

    /**
     * 中断停止
     * 对于sleep和wait通过可以中断线程
     * @throws InterruptedException
     */
    public static void interruptStop() throws InterruptedException {
        Thread t1=new Thread(()->{
            System.out.println("start t1");
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("run t1");
            }
            System.out.println("end t1 isInterrupted before:"+Thread.currentThread().isInterrupted());//get
            System.out.println("end t1 interrupted:"+Thread.currentThread().interrupted());//复位状态 set+get
            System.out.println("end t1 isInterrupted end:"+Thread.currentThread().isInterrupted());//判断线程是否被中断，返回ture代表线程被中断了，只是去获取一下，不会该表中断标志位
        });
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t1.interrupt();//set

    }

    /**
     * 中断被吞之后，再次中断该线程
     * @throws InterruptedException
     */
    public static void interruptDoubleStop() throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("start run");
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //外部中断异常之后,再次中断，防止中断信号被吞掉
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        thread.interrupt();
        System.out.println("interruptDoubleStop");
    }

    /**
     * volatile 修饰标记位来进行停止线程
     */
}
