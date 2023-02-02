package com.kuark.tool.model.concurrents.exceples.example2;

/**
 * Created by caoqingyuan on 2017/6/22.
 */
public class ThreadA implements Runnable {
    private String data;
    public ThreadA(String data){
        this.data=data;
    }
    @Override
    public void run() {
        try {
            Contants.queue.put(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
