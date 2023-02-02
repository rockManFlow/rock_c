package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/31.
 */
public class SynThreadB extends Thread {
    private TestSyn testSyn;
    public SynThreadB(TestSyn testSyn){
        this.testSyn=testSyn;
    }
    public void run(){
        int num = testSyn.getNum();
        System.out.println("num="+num);
        try {
            Thread.sleep(4000);
            System.out.println("num min="+testSyn.getNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testSyn.setNum(33);
        System.out.println("num after="+testSyn.getNum());
    }
}
