package com.kuark.tool.model.concurrents.locks;

/**
 * 使用ReentrantLock锁来实现等待和通知--Condition类
 * synchronized使用wait/notify,notifyall实现的
 * Created by caoqingyuan on 2017/7/26.
 */
public class WaitNotifyReen extends Thread{
    private MyServer myServer;
    private String st;
    public WaitNotifyReen(MyServer myServer,String st){
        this.myServer=myServer;
        this.st=st;
    }
    public void run(){
        if(st.equals("A")) {
            myServer.waitA();
        }else {
            myServer.waitB();
        }
    }

}
