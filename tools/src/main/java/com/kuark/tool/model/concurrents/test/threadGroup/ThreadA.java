package com.kuark.tool.model.concurrents.test.threadGroup;

/**
 * Created by caoqingyuan on 2017/7/28.
 */
public class ThreadA extends Thread {
    public void run(){
        System.out.println("ThreadA start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadA end");
    }
}
