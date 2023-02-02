package com.kuark.tool.model.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by caoqingyuan on 2016/9/14.
 */
public class Worker implements Runnable {
    //这个方法是如果与compareAndSet(first,secend)中first相同，则返回true，并把AtomicBoolean对象值至为secend。不相同返回false
    private static AtomicBoolean PROCESSING_FLAG = new AtomicBoolean(false);
    private String name;
    public Worker(String name){
        this.name=name;
    }
    @Override
    public void run() {
        if(PROCESSING_FLAG.compareAndSet(false,true)){
            try {
                Thread.sleep(2*1000);
                System.out.println("dhdhdh");
                PROCESSING_FLAG.set(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        AbstractApplicationContext context =new ClassPathXmlApplicationContext("SpringBeans.xml");
    }
}
