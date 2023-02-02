package com.kuark.tool.base.javaAPI.concurrent.executorService;

import com.kuark.tool.base.javaAPI.concurrent.locks.ThreadBase;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO java.util.concurrent中线程池和线程的使用
 * Created by caoqingyuan on 2016/8/12.
 */
@Slf4j
public class BaseMain {
    public static void main1(String[] args){
        //newFixedThreadPool方法可以创建指定大小线程数的线程池。该线程池中的线程，如果不显示的关闭该线程，该线程将会一直存在
        //如果线程池个数已经被占用完，新进来的线程就会替换已经执行完的线程，来并发执行
        ExecutorService executorFixed= Executors.newFixedThreadPool(1);
        //newCacheThreadPool方法的特点是：线程池中的线程数是不定的，但如果一个线程在60s期间没有被执行，就会被自动的从线程池中去除出去。
        //ExecutorService executorCache=Executors.newCachedThreadPool();
        //如果线程池已满，新来的线程就会被缓存在线程池队列中，等待线程池执行
        CallSecTask task2=new CallSecTask();
        //把任务进行提交，线程在线程池中就会并发的执行
        executorFixed.submit(task2);
        CallTask task1=new CallTask();
        //FutureTask实现了Runnable+Future，所以该对象是既可以当做一个线程使用，也可以当做线程返回的结果处理
        FutureTask resultTask=(FutureTask)executorFixed.submit(task1);
//        executorFixed.submit(task1);
//        executorFixed.submit(task2);//线程池关闭之后再执行submit就会出错
        executorFixed.shutdown();//关闭线程池。不关闭线程池会一直运行
        System.out.println("task end");
    }
    public void test(){
        //ExecutorService接口的具体实现类的使用
//        ExecutorService service=new ThreadPoolExecutor(3,10,5*1000, TimeUnit.SECONDS,);
        //ScheduledExecutorService接口的实现类，具有延后执行线程或者定时多次执行线程
        ScheduledExecutorService threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        //具体用法到使用时再查
    }
    public static void main(String[] args){
//        System.out.println("startmain");
//        //ReadWriteLock接口的实现，读写锁的实现
//        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
//        readWriteLock.writeLock().lock();
//        new Thread(new ThreadBase()).start();
//        new Thread(new ThreadBase()).start();
//        readWriteLock.writeLock().unlock();
//        System.out.println("endmain");

//        ExecutorService executorCache=Executors.newCachedThreadPool();
//        Executors.newFixedThreadPool(1)



    }
}
