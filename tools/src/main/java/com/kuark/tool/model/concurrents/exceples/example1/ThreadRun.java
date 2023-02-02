package com.kuark.tool.model.concurrents.exceples.example1;

/**
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75.
 * Created by caoqingyuan on 2017/6/22.
 */
public class ThreadRun implements Runnable {
    public static int count = 0;
    private Object pre;
    private Object self;
    private String lineName;

    public ThreadRun(String lineName, Object pre, Object self) {
        this.lineName = lineName;
        this.pre = pre;
        this.self = self;
    }

    @Override
    public void run() {
        while (count <= 75) {
            synchronized (pre) {
                synchronized (self) {
                    if (count != 75) {
                        for (int i = 0; i < 5; i++) {
                            ++count;
                            System.out.println(lineName + ":" + count);
                        }
                    }
                    self.notify();//唤醒由self暂停的线程
                }
                if (count == 75) {
                    //最后关闭所有的线程
                    System.out.println("关闭"+lineName);
                    break;
                }
                try {
                    pre.wait();//使用pre来暂停当前线程，解锁pre
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] s) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        new Thread(new ThreadRun("线程1", c, a)).start();
        Thread.sleep(100);
        new Thread(new ThreadRun("线程2", a, b)).start();
        Thread.sleep(100);
        new Thread(new ThreadRun("线程3", b, c)).start();
    }
}
