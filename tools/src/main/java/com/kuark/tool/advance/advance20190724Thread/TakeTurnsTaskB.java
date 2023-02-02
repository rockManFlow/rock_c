package com.kuark.tool.advance.advance20190724Thread;

/**
 * @author caoqingyuan
 * @detail 多个线程轮流执行
 * @date 2019/7/25 10:08
 */
public class TakeTurnsTaskB implements Runnable{
    private Integer synA;
    private Integer synB;
    public TakeTurnsTaskB(Integer synA,Integer synB){
        this.synA=synA;
        this.synB=synB;
    }
    @Override
    public void run() {
        for(int i=0;i<50;i++) {
            synchronized (synB) {
                System.out.println("Run B="+i);
                synchronized (synA){
                    synA.notify();
                }
                try {
                    synB.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("B Finish");
        synchronized (synA){
            synA.notify();
        }
    }
}
