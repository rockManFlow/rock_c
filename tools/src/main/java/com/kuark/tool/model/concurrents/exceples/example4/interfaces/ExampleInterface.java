package com.kuark.tool.model.concurrents.exceples.example4.interfaces;

/**
 * Created by caoqingyuan on 2017/6/21.
 */
public abstract class ExampleInterface implements Runnable{
    public abstract void service();
    public void run(){
        service();
    }
}
