package com.kuark.tool.advance.advance20190724Thread;

/**
 * @author caoqingyuan
 * @detail 多个线程轮流执行
 * @date 2019/7/25 10:08
 */
public class TakeTurnsTaskA implements Runnable{
    private Integer synA;
    private Integer synB;
    public TakeTurnsTaskA(Integer synA,Integer synB){
        this.synA=synA;
        this.synB=synB;
    }
    @Override
    public void run() {
        for(int i=0;i<50;i++) {
            synchronized (synA) {
                System.out.println("Run A="+i);
                synchronized (synB){
                    synB.notify();
                }
                try {
                    synA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("A Finish");
        synchronized (synB){
            synB.notify();
        }
    }
}
