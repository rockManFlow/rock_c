package com.kuark.tool.model.thread;

import java.util.concurrent.Callable;

/**
 * 每个线程子任务都由这个子任务线程来接收运行
 * Created by caoqingyuan on 2016/9/9.
 */
abstract class ExecuteTask implements Callable{
    public Void call() throws Exception{
        run();
        return null;
    }
    abstract void run();
}
