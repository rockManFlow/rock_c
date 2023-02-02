package com.kuark.tool.base.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/5/6 10:11
 */
public class ThreadPoolTest {
    //这样具体引用哪个实现包就使用哪个
    private static final Logger log= LoggerFactory.getLogger("ThreadPoolTest");
    public static void main(String[] args){
        //线程池管理线程，例如：线程何时执行，销毁。控制线程创建的个数
        //线程可重用，则重用。60s未被使用，则销毁
        Executors.newCachedThreadPool();
        //创建指定数量的线程池，多的放到线程队列中
        Executors.newFixedThreadPool(10);
        //可以保证线程延迟执行等
        Executors.newScheduledThreadPool(10);
        //创建单个线程池，保证线程顺序执行
        Executors.newSingleThreadExecutor();
    }
}
