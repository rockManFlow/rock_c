package com.kuark.tool.advance.advance20201111;

/**
 * @author rock
 * @detail ≤‚ ‘À¿À¯ºÏ≤‚π§æﬂjClose °¢jstack
 * @date 2021/2/18 18:28
 */
public class CheckDeadLockTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (B.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A.class) {

                    }
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (A.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    synchronized (B.class) {

                    }
                }

            }
        }).start();
    }

    class A {

    }

    class B {

    }
}
