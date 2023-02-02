package com.kuark.tool.model.concurrents.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caoqingyuan on 2017/5/8.
 */
public class BaseLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private Integer num = 0;
    private Integer aa = 0;
    private static BaseLock bb = null;

    public static BaseLock getBaseLock(int i) {
        System.out.println(i);
        lock.lock();
        try {
            if (bb == null) {
                System.out.println("getBaseLock");
                bb = new BaseLock();
            }
        }finally {
            lock.unlock();
        }
        return bb;
    }

    public void main() {
        lock.lock();
        try {
            //方法体
            System.out.println("多线程执行加锁的方法");
            nn();
            num++;
            System.out.println("num=" + num);
        } finally {
            lock.unlock();
        }
    }
    public void nn(){
        System.out.println("nn");
        lock.lock();
        lock.unlock();
    }

    public void mbin() {
        //方法体
        System.out.println("mbin");
        aa++;
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("mbin aa=" + aa);
    }
}
