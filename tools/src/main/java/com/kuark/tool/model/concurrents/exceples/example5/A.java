package com.kuark.tool.model.concurrents.exceples.example5;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class A implements Runnable {
    private String lock;
    private String threadName;
    public A(String lock,String threadName){
        this.lock=lock;
        this.threadName=threadName;
    }
    @Override
    public void run() {
        while (true)
        synchronized (lock){
            if(Constants.isRun.get()){
                DbSinumate.writeDB("A",threadName);
                Constants.isRun.set(false);
                lock.notifyAll();
            }
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
