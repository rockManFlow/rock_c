package com.kuark.tool.base.javaAPI.concurrent.executorService;

/**
 * synchronized是对共享的资源进行加锁，防止并发导致结果不准确
 * Created by caoqingyuan on 2016/8/12.
 */
public class Runs {
    static Thread[] threads = new Thread[1000];
    public static void main(String[] s){
        final Amount am=new Amount("xiao",1000);
        for(int i=0;i<1000;i++){
            threads[i]=new Thread(new Runnable(){
                public void run(){
                    try {
                        am.income(100);
                        Thread.sleep(2*10L);
                        am.getMoney(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
        for (int i=0; i<1000; i++){
            try {
                threads[i].join(); //等待所有线程运行结束
            } catch (InterruptedException e) {
                // ignore
            }
        }
        System.out.println("sum name="+am.name);
        System.out.println("sum money="+am.money);
    }
}
