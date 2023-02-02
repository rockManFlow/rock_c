package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class Consume1 implements Runnable {
    private String lock;
    private String name;
    public Consume1(String lock,String name){
        this.lock=lock;
        this.name=name;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (Constants.list.size() != 0) {
                    Constants.list.remove(0);
                    System.out.println(name+":consume get data");
//                    lock.notify();
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
}
