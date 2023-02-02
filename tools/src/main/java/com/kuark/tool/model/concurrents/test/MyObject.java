package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class MyObject {
    private Object a=new Object();
    private Object b=new Object();
    public void methodA(){
        synchronized (a) {
            System.out.println("a 线程执行完methodA");
            try {
                Thread.sleep(2 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b){
                System.out.println("a 线程执行完methodA end");
            }
        }
    }
    public void methodB(){
        synchronized (b) {
            System.out.println("b 线程执行完methodA");
            try {
                Thread.sleep(2 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a){
                System.out.println("b 线程执行完methodA end");
            }
        }
    }
    public void hhh(){

    }
}
