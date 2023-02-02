package com.kuark.tool.model.concurrents.test.threadGroup;

/**
 * Created by caoqingyuan on 2017/7/28.
 */
public class Runs {
    public static void main(String[] s){
        ThreadGroup group=new ThreadGroup("rock");
        ThreadA a=new ThreadA();
        ThreadB b=new ThreadB();
        //把对象加入到线程组中
        Thread at=new Thread(group,a);
        Thread bt=new Thread(group,b);
        at.start();
        bt.start();
        System.out.println("活动线程数："+group.activeCount());
        System.out.println("线程组名称："+group.getName());
    }
}
