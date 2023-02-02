package com.kuark.tool.base.javaAPI.concurrent.locks;

/**
 * Created by caoqingyuan on 2017/3/13.
 */
public class ThreadBase implements Runnable{

    public static Integer num=0;
    public void setNum(int num){
        this.num=num;
    }
    public Integer getNum(){
        return num;
    }
    @Override
    public void run() {
        System.out.println("start="+num);
        try {
            Thread.sleep(5*1000);
            setNum(num+2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end="+num);
    }
}
