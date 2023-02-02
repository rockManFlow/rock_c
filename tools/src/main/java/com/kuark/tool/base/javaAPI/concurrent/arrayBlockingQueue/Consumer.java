package com.kuark.tool.base.javaAPI.concurrent.arrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by caoqingyuan on 2017/3/9.
 */
public class Consumer implements Runnable {
    BlockingQueue queue;
    public Consumer(BlockingQueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        try{
            System.out.println("data="+queue.take());
            System.out.println("data="+queue.take());//如果队列是空的，会一直阻塞，直到有对象或者程序强行终止
            System.out.println("data="+queue.take());
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
