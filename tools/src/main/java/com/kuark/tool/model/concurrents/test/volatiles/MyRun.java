package com.kuark.tool.model.concurrents.test.volatiles;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class MyRun {
    public static void main(String[] s) throws InterruptedException {
//        T1 t1 = new T1();
//        Thread t=new Thread(t1);
//        t.start();
//        Thread.sleep(3000);
//        System.out.println("stop");
//        t1.setAc(false);
        T2 t2=new T2();
        Thread a=new Thread(t2);
        a.start();
        Thread b=new Thread(t2);
        b.start();
        Thread c=new Thread(t2);
        c.start();
        Thread d=new Thread(t2);
        d.start();
        Thread e=new Thread(t2);
        e.start();
    }
}
