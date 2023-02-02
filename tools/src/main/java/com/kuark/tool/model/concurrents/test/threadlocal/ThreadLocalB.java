package com.kuark.tool.model.concurrents.test.threadlocal;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class ThreadLocalB extends Thread{
    public void run(){
        System.out.println("ThreadLocalB start");
        System.out.println("ThreadLocalB before"+ Constants1.local.get());
        Constants1.local.set("ThreadLocalB");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadLocalB after"+ Constants1.local.get());
    }
}
