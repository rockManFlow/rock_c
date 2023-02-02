package com.kuark.tool.base.task;

/**
 * Created by caoqingyuan on 2016/8/24.
 */
public class TimerTest {
    public static void main(String[] args){
        for(int i=0;i<2;i++){
            MyTimerTask.schdule();
            try {
                System.out.println("main start");
                Thread.sleep(15*1000);
                System.out.println("main end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
