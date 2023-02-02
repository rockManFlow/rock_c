package com.kuark.tool.base.javaAPI.concurrent.arrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by caoqingyuan on 2017/3/9.
 */
public class Producter implements Runnable {
    BlockingQueue queue;
    public Producter(BlockingQueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        try {
            queue.add("1");
            Thread.sleep(5 * 1000);
            queue.add("2");
            queue.add("3");
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
