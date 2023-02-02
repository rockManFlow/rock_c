package com.kuark.tool.advance.advance20190724Thread.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rock
 * @detail 阻塞队列通过Lock锁实现
 * @date 2021/2/5 15:18
 */
public class BlockQueueUpgrade<T> {
    private int size;
    private Queue<T> queue;
    private ReentrantLock lock=new ReentrantLock();
    //绑定状态
    private Condition condition=lock.newCondition();

    public BlockQueueUpgrade(int num){
        this.size=num;
        this.queue=new LinkedList<T>();
    }

    public void add(T t) throws InterruptedException{
        lock.lock();
        try{
            while (this.queue.size()>=this.size){
                condition.await();
            }
            queue.add(t);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        lock.lock();
        try{
            while (this.queue.isEmpty()){
                condition.await();
            }
            T t=queue.poll();
            condition.signalAll();
            return t;
        }finally {
            lock.unlock();
        }
    }
}
