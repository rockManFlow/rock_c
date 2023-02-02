package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class JoinThread implements Runnable {
    @Override
    public void run() {
        System.out.println("test join run");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test join end");
    }
}
