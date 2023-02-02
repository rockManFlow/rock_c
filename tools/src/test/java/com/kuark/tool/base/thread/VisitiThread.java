package com.kuark.tool.base.thread;

/**
 * @author caoqingyuan
 * @detail 线程可见性
 * @date 2019/5/15 18:57
 */
public class VisitiThread {
    //volatile
    private static boolean isOver = false;

    private static int number = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) {
                    //Thread.yield();
                }
                System.out.println(number);
            }
        });
        thread.start();
        Thread.sleep(50);
        number = 50;
        isOver = true;
        System.out.println("end");
    }
}
