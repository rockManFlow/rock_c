package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class JoinThread2 implements Runnable {
    private Thread thread;
    public JoinThread2(Thread thread){
        this.thread=thread;
    }
    @Override
    public void run() {
        synchronized (thread) {
            System.out.println("JoinThread2 test join run");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("JoinThread2 test join end");
        }
    }
}
