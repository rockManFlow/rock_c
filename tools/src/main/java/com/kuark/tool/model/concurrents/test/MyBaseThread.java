package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class MyBaseThread extends Thread{
    private String st;
    private MyObject object;
    public MyBaseThread(MyObject object,String st){
        this.st=st;
        this.object=object;
    }
    public void run(){
        if("a".equals(st)){
            object.methodA();
        }else{
            object.methodB();
        }
    }
}
