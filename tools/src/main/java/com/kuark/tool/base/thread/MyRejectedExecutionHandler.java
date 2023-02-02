package com.kuark.tool.base.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author caoqingyuan
 * @detail 拒绝线程处理方式
 * @date 2019/6/18 20:42
 */
@Slf4j
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("Current Thread HashCode:{},ThreadPoolActiveNum:{}",r.hashCode(),executor.getActiveCount());
    }
}
