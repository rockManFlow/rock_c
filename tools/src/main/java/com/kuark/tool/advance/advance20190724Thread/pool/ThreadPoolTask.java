package com.kuark.tool.advance.advance20190724Thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail ScheduledExecutorService用于执行延迟或者定时任务
 * scheduleAtFixedRate或scheduleWithFixedDelay
 * @date 2020/10/30 10:32
 */
@Slf4j
public class ThreadPoolTask {

    public static void main(String[] args) {
        //
        ScheduledExecutorService scheduledExecutor=new ScheduledThreadPoolExecutor(10);
        //1s之后执行，之后任务间隔20S来定时执行
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("running");
            }
        },1L,20, TimeUnit.SECONDS);
    }
}
