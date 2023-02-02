package com.kuark.tool.model.concurrents.test.waitNotify;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class TestWait {
    public static void main(String[] s){
        String lock=new String("lock");
        System.out.println("main start");
        synchronized (lock){
            try {
                System.out.println("wait before");
                lock.wait();//执行wait之后，其后面的代码就不执行了，当前线程就暂停了
                System.out.println("wait after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main end");
    }
}
