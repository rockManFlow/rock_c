package com.kuark.tool.base.test;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/1/2 15:38
 */
public class ThreadVo {
    private static String name;
    ThreadVo(String name){
        this.name=name;
    }
    synchronized public static void show() throws InterruptedException {
        System.out.println("start run"+name);
        int num=0;
        while (true){
            num=num+1;
            if(num%1000000000==0) {
                System.out.println("Name:" + name + "|num:" + num);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        ThreadVo v1=new ThreadVo("AA");
        ThreadB b=new ThreadB(v1);
        b.start();

        ThreadVo v2=new ThreadVo("BB");
        ThreadB b2=new ThreadB(v2);
        b2.start();

        Thread.sleep(1*1*1000);
        b.interrupt();
        System.out.println("b:"+b.isInterrupted());

        Thread.sleep(1*1*1000);
        b2.interrupt();
        System.out.println("b2:"+b2.isInterrupted());

        System.out.println("main end");
    }
}
