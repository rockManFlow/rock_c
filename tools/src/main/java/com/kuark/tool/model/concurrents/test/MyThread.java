package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/23.
 */
public class MyThread extends Thread {
    private Integer num=10;
     public void run(){
         try {
             Thread.sleep(3000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println("sum time=");
    }
}
