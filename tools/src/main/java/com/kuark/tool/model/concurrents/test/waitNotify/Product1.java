package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class Product1 implements Runnable{
    private String lock;
    private String name;
    public Product1(String lock,String name){
        this.lock=lock;
        this.name=name;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (Constants.list.size() == 0) {
                    Constants.list.add(1);
                    System.out.println(name+":producter put data");
                    lock.notify();
//                    lock.notifyAll();
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
