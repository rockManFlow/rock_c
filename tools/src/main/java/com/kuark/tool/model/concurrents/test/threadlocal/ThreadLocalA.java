package com.kuark.tool.model.concurrents.test.threadlocal;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class ThreadLocalA extends Thread{
    public void run(){
        System.out.println("ThreadLocalA start");
        System.out.println("ThreadLocalA before"+ Constants1.local.get());
    }
}
