package com.kuark.tool.model.concurrents.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caoqingyuan on 2017/7/26.
 */
public class MyServer {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition conditionB=lock.newCondition();

    public void waitA() {
        lock.lock();
        try {
            System.out.println("waitA AAAAAA："+lock.getHoldCount());
//            condition.await();
            server();
            System.out.println("waitA BBBBBB");
        } finally {
            lock.unlock();
            System.out.println("waitA 锁释放");
        }
    }

    private void server(){
        lock.lock();
        System.out.println("server:"+lock.getHoldCount());
        lock.unlock();
    }

    public void waitB() {
        lock.lock();
        try {
            System.out.println("waitB AAAAAA");
            conditionB.await();
            System.out.println("waitB BBBBBB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("waitB 锁释放");
        }
    }

    public void signal() {
        lock.lock();
        System.out.println("signal AAAAAA");
        condition.signal();
        System.out.println("signal BBBBBB");
        lock.unlock();
        System.out.println("signal 锁释放");
    }

    public void signalAll() {
        lock.lock();
        System.out.println("signalAll AAAAAA");
        condition.signalAll();
        System.out.println("signalAll BBBBBB");
        lock.unlock();
        System.out.println("signalAll 锁释放");
    }
    public void signalAll_A() {
        lock.lock();
        System.out.println("signalAll_A AAAAAA");
        condition.signalAll();
        System.out.println("signalAll_A BBBBBB");
        lock.unlock();
        System.out.println("signalAll_A 锁释放");
    }
    public void signalAll_B() {
        lock.lock();
        System.out.println("signalAll_B AAAAAA");
        conditionB.signalAll();
        System.out.println("signalAll_B BBBBBB");
        lock.unlock();
        System.out.println("signalAll_B 锁释放");
    }
}
