package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class T3 implements Runnable {
    private Object lock;
    public T3(Object lock){
        this.lock=lock;
    }
    @Override
    public void run() {
        while (true)
        synchronized (lock){
            try {
                if(TestMain.i!=0){
                    lock.wait();
                }
                System.out.println("T3 i:"+TestMain.i);
                TestMain.i=1;
                lock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
