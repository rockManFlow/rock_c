package com.kuark.tool.advance.advance20190724Thread.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author rock
 * @detail 同步队列
 * @date 2021/5/27 14:42
 */
public class SyncQueueMain {
    public static void main(String[] args) {
        /**
         * SynchronousQueue是BlockingQueue的一种，所以SynchronousQueue是线程安全的。
         * SynchronousQueue和其他的BlockingQueue不同的是SynchronousQueue的capacity是0。即SynchronousQueue不存储任何元素。
         *
         * 也就是说SynchronousQueue的每一次insert操作，必须等待其他线性的remove操作。而每一个remove操作也必须等待其他线程的insert操作。同步队列
         */
        //同步队列
        SynchronousQueue synchronousQueue=new SynchronousQueue();
    }
}
