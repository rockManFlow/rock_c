package com.kuark.tool.model.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caoqingyuan on 2016/9/9.
 */
public class ThreadPoolTask {
    //线程池对象
    private static final ExecutorService executorCache= Executors.newCachedThreadPool();
    //提交线程
    public void submit(Callable task){
        executorCache.submit(task);
    }
}
