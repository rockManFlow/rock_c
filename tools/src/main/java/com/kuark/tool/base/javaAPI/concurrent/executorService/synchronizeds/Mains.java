package com.kuark.tool.base.javaAPI.concurrent.executorService.synchronizeds;

import org.apache.log4j.Logger;

/**
 * synchronized的用法
 * Created by caoqingyuan on 2016/11/2.
 */
public class Mains extends Thread{
    private static final Logger logger=Logger.getLogger(Mains.class);

    private int threadNo;
    private Integer lock;
    public Mains(int threadNo,int lock) {
        this.threadNo = threadNo;
        this.lock=lock;
    }
    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 5; i++) {
            new Mains(i,i).start();
        }
    }

    @Override
    public void run() {
        try {
        synchronized ("11") {//因为多个线程lock属于同一个对象
            Thread.sleep(1 * 1000);
            for (int i = 1; i < 10; i++) {
                System.out.println("No." + threadNo + ":" + i);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
