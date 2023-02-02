package com.kuark.tool.model.concurrents.test.threadGroup;

/**
 * Created by caoqingyuan on 2017/7/28.
 */
public class ThreadB extends Thread {
    public void run(){
        System.out.println("ThreadB start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadB end");
    }
}
