package com.kuark.tool.advance.advance20190724Thread.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rock
 * @detail 阻塞队列两种方式实现
 * @date 2022/1/24 17:17
 */
public class BlockQueueUpgrade2<T> {
    private Integer size;
    private Queue<T> queue;
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public BlockQueueUpgrade2(Integer size){
        queue=new LinkedList<T>();
        this.size=size;
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() > size) {
                condition.await();
            }
            queue.add(t);
            condition.notifyAll();
        }finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            T t = queue.poll();
            condition.notifyAll();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public synchronized void add2(T t) throws InterruptedException {
        while (queue.size()>size){
            this.wait();
        }
        queue.add(t);
        this.notifyAll();
    }

    public synchronized T get2() throws InterruptedException {
        while (queue.isEmpty()){
            this.wait();
        }
        T t=queue.poll();
        this.notifyAll();
        return t;
    }
}
