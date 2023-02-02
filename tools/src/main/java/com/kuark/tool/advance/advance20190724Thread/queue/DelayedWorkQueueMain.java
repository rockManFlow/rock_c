package com.kuark.tool.advance.advance20190724Thread.queue;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rock
 * @detail 延迟队列DelayQueue使用及实现原理理解
 * @date 2021/7/7 17:31
 */
public class DelayedWorkQueueMain {
    public static void main(String[] args) throws InterruptedException {
        // 创建延时队列
        DelayQueue<DelayedMessage> queue = new DelayQueue<DelayedMessage>();
        // 添加延时消息,m1 延时3s
        DelayedMessage m1 = new DelayedMessage("world", 3000);
        // 添加延时消息,m2 延时10s
        DelayedMessage m2 = new DelayedMessage("hello", 10000);
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);

        queue.take();//不存在任务就阻塞等待
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        long delayTime=3000;
        //等待指定的纳秒数之后唤醒
        condition.awaitNanos(TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime());
        condition.await(1,TimeUnit.SECONDS);//阻塞指定时间之后唤醒
    }

    private void priorityQueue(){
        PriorityQueue priorityQueue=new PriorityQueue();
        PriorityBo b=new PriorityBo();
        priorityQueue.add(b);
    }

    class PriorityBo{

    }


    //简单使用
    /**
     * 初始化
     */
    public void init() {
        Thread daemonThread = new Thread(() -> {
            execute();
        });
        daemonThread.setName("DelayQueueMonitor");
        daemonThread.start();
    }
    private void execute() {
        DelayQueue<DelayedMessage> delayQueue = new DelayQueue<DelayedMessage>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        while (true) {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            System.out.println("当前存活线程数量:" + map.size());
            int taskNum = delayQueue.size();
            System.out.println("当前延时任务数量:" + taskNum);
            try {
                // 从延时队列中获取任务--队首元素，没到时间会阻塞，直到延迟指定时间
                DelayedMessage delayOrderTask = delayQueue.take();
                if (delayOrderTask != null) {
                    Runnable task = delayOrderTask.getTask();
                    if (null == task) {
                        continue;
                    }
                    // 提交到线程池执行task
                    executor.execute(task);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
