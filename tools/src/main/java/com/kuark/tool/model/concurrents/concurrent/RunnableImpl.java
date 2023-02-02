package com.kuark.tool.model.concurrents.concurrent;


import com.kuark.tool.model.concurrents.locks.BaseLock;

/**
 * Created by caoqingyuan on 2017/5/8.
 */
public class RunnableImpl implements Runnable {
    private int i=0;
    public RunnableImpl(int i){
        this.i=i;
    }
    @Override
    public void run() {
        System.out.println("Runnable Run");
        BaseLock bb=BaseLock.getBaseLock(i);
        System.out.println(bb);
        bb.main();
        bb.mbin();
    }
}
