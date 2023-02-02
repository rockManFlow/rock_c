package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/31.
 */
public class SynMain {
    public static void main(String[] s) throws InterruptedException {
        TestSyn syn=new TestSyn();
        syn.setNum(1);
        SynThreadA a=new SynThreadA(syn);
        SynThreadB b=new SynThreadB(syn);
        b.start();
        Thread.sleep(1000);
        a.start();
    }
}
