package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/23.
 */
public class Run {
    public static void main(String[] s) throws InterruptedException {
        //线程之间的交互，其中一个方式
//        MyThread myThread=new MyThread();
//        Thread a=new Thread(myThread,"A");
//        Thread b=new Thread(myThread,"B");
//        Thread c=new Thread(myThread,"C");
//        Thread d=new Thread(myThread,"D");
//        Thread e=new Thread(myThread,"E");
//        a.start();
//        b.start();
//        c.start();
//        d.start();
//        e.start();
//        System.out.println("id:"+Thread.currentThread().getId());
//        MyThread thread=new MyThread();
//        thread.start();
//        MyObject object=new MyObject();
//        MyBaseThread a=new MyBaseThread(object,"a");
//        MyBaseThread b=new MyBaseThread(object,"b");
//        a.start();
//        b.start();
//        System.out.println("thread end");

        MyThread myThread=new MyThread();
        myThread.start();
        Thread.State state = myThread.getState();
        System.out.println("state="+state);
        if(state==Thread.State.RUNNABLE){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }
}
