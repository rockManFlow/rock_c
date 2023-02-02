package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.TimeUnit;

/**
 * @author caoqingyuan
 * @detail 两个线程轮流执行
 * @date 2019/7/25 10:09
 */
public class TakeTurnsMain {
    public static void main(String[] args) throws InterruptedException {
        //保证不同的两个对象
        Integer synA=new Integer(0);
        Integer synB=new Integer(1);
        Thread threadA=new Thread(new TakeTurnsTaskA(synA,synB));
        Thread threadB=new Thread(new TakeTurnsTaskB(synA,synB));
        threadA.start();
        //todo 得有一个停顿，使一个线程先调用wait方法来释放锁对象，所以wait会释放锁对象
        TimeUnit.SECONDS.sleep(3);
        threadB.start();
    }
}
