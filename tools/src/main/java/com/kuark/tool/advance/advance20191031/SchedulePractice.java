package com.kuark.tool.advance.advance20191031;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2019/11/11 10:48
 */
@Log4j
public class SchedulePractice {
    public static void main(String[] args){
        /**
         * 通过线程池定时执行指定任务
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("schedule-thread-"+thread.getId());
                return thread;
            }
        });
        scheduledExecutorService.scheduleAtFixedRate(new ScheduleRunnable("1"),1L,3L, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new ScheduleRunnable("2"),1L,3L, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(new ScheduleRunnable("3"),1L,3L, TimeUnit.SECONDS);
        log.info("Main end");
    }
}
