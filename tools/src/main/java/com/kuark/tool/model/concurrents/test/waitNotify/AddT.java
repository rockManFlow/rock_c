package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class AddT implements Runnable {
    private Object lock;

    public AddT(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("notify wait before");
            for(int i=0;i<10;i++){
                if(i==5){
                    lock.notify();//
                    System.out.println("notify");
                }
                System.out.println("i="+i);
            }
            System.out.println("notify wait after");
        }
    }
}
