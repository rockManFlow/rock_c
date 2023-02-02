package com.kuark.tool.model.concurrents.test.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class T2 implements Runnable {
    private AtomicInteger count=new AtomicInteger(0);
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("count:"+count.incrementAndGet());
        }

    }
}
