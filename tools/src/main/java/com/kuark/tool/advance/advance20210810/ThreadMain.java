package com.kuark.tool.advance.advance20210810;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.framework.AopContext;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author rock
 * @detail
 * @date 2021/8/10 15:16
 */
public class ThreadMain {
    public static void main1(String[] args) {
        ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        readLock.lock();
        System.out.println("get read lock");
        writeLock.lock();
        System.out.println("get write lock");
        readLock.unlock();
        writeLock.unlock();

        AopContext.currentProxy();

        HashMap map=new HashMap(4);
        List list=new ArrayList(2);
    }

    public static void main(String[] args) {
//        int sizeFor = tableSizeFor(5);
//        System.out.println(sizeFor);
//        String s = UUID.randomUUID().toString().replaceAll("-","");
//        System.out.println(s);
//
//        Set<Long> sa=new HashSet<>(3);
//        sa.add(10L);
//        sa.add(12L);
//        sa.add(3L);
//        System.out.println(JSON.toJSONString(sa));

        String credentialsString = "mdpbanktest" + ":" + "P@ssw0rd1234";
        byte[] encodedBytes = Base64.getEncoder().encode(credentialsString.getBytes(Charset.defaultCharset()));
        String encodedCredentials = new String(encodedBytes, Charset.defaultCharset());
        System.out.println(encodedCredentials);
    }

    private static int tableSizeFor(int cap){
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static void threadLocal(){
        ThreadLocal local=new ThreadLocal();
        local.set("");
    }

    private static void aqs(){
        /**
         *
         *
         * ===============================
         * ReentrantLock 重入锁，属于独占锁
         * 其读锁属于共享锁
         * ===============================
         * Condition
         *上面lock.newCondition()其实是new一个AQS中ConditionObject内部类的对象出来，这个对象里面有一个队列，
         * 当调用await()方法的时候会存入一个Node节点到这个队列中，并且调用park()方法阻塞当前线程，释放当前线程的锁。
         * 而调用singal()方法则会移除内部类中的队列头部的Node，然后放入AQS中的队列中等待执行机会
         */

        ReentrantLock lock=new ReentrantLock();
        lock.lock();
    }
}
