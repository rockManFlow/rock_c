package com.kuark.tool.model.concurrents.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by caoqingyuan on 2017/7/26.
 */
public class MySever2 {
    private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void rr(){
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();//获取读锁
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();//获取写锁
    }
}
