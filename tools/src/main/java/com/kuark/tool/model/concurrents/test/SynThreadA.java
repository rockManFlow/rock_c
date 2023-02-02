package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/31.
 */
public class SynThreadA extends Thread {
    private TestSyn testSyn;
    public SynThreadA(TestSyn testSyn){
        this.testSyn=testSyn;
    }
    public void run(){
        int num = testSyn.getNum();
        System.out.println("SynThreadA num="+num);
        testSyn.setNum(22);
        System.out.println("SynThreadA num after="+testSyn.getNum());
    }
}
