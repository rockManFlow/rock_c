package com.kuark.tool.base.javaAPI.concurrent;

import com.kuark.tool.base.javaAPI.concurrent.arrayBlockingQueue.Consumer;
import com.kuark.tool.base.javaAPI.concurrent.arrayBlockingQueue.Producter;
import com.kuark.tool.base.javaAPI.concurrent.delayQueue.Experiment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Exchanger;

/**
 * Created by caoqingyuan on 2017/3/9.
 */
public class QueueMain {
    //有界阻塞队列的测试
    public static final ArrayBlockingQueue queue=new ArrayBlockingQueue(10);
    public static void main1(String[] args){
        System.out.println("start");
        Producter producter=new Producter(queue);
        Consumer consumer=new Consumer(queue);
        new Thread(producter).start();
        new Thread(consumer).start();
        System.out.println("end");
    }
    public static void main2(String[] args){
        DelayQueue delayQueue=new DelayQueue();
        Experiment e=new Experiment();
        delayQueue.add(e);
    }
    public static void main(String[] args){
        Exchanger exchanger=new Exchanger();
    }
}
