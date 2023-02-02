package com.kuark.tool.advance.advance20190827;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rock
 * @detail
 * @date 2019/8/27 20:31
 */
public class AtomicReferenceTest {
    public static void main(String[] args){
        /**
         * AtomicReference是作用是对"对象"进行原子操作。
         * 注意是对象本身的操作，对于对象内部成员变量修改
         */

        AtomicReference<ExpEntity> expReference=new AtomicReference<ExpEntity>();
        ExpEntity expEntity = expReference.get();

        //cas
        AtomicInteger atomicInteger=new  AtomicInteger(1);
        new Thread(()->{

        }).start();
        atomicInteger.getAndIncrement();
        atomicInteger.get();

        /**
         * AbstractQueuedSynchronizer 同步队列
         * 这个队列有个自己的state获取标识，0其他线程可获取，1表示已有线程获取
         * 各个锁对象内部都有自己的AQS，多个线程使用相同锁对象时，使用的是同一个同步队列。
         * 没有获取到的锁对象，会放到这个AQS中（通过cas来进行放到队列尾部，可能会有多个线程同时存放）
         * 各个线程都有一个waitstatus表示当前线程的状态。如果是取消状态，需要从队列中清除。
         * SIGNAL 等待触发状态。等待队列是FIFO先进先出，只有前一个节点的状态为SIGNAL时，当前节点的线程才能被挂起（使用UNSAFE.park来挂起线程）。
         * 释放锁state设置0，从AQS中取出SIGNAL的线程，先进先出（当前新进线程可能是否公平锁）来获取state状态
         */
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
    }
}
