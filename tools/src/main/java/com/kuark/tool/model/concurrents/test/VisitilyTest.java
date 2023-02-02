package com.kuark.tool.model.concurrents.test;

import org.junit.Test;

/**
 * 可见性验证
 * Created by caoqingyuan on 2017/5/31.
 */
public class VisitilyTest {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        public void run(){
            while (!ready){
                System.out.println("yield");
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    @Test
    public void hhh(){
        System.out.println(""+Thread.currentThread().getName());
    }

    public static void main(String[] s) throws InterruptedException {
        System.out.println(":"+Thread.currentThread().getName());
        new ReaderThread().start();
        Thread.sleep(1000L);
        number=42;
        ready=true;
    }
}
