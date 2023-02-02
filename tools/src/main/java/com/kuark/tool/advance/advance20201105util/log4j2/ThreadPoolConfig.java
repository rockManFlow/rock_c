package com.kuark.tool.advance.advance20201105util.log4j2;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @author rock
 * @detail 重写线程池来使log4j2线程池中子线程可以使用当前主线程的traceID并不影响其他任务使用
 * @date 2020/11/20 14:29
 */
public class ThreadPoolConfig {
    @Bean
    public Executor getAsyncExecutor() {
        // 对线程池进行包装，使之支持traceId透传
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() {
            @Override
            public <T> Future<T> submit(Callable<T> task) {
                // 传入线程池之前先复制当前线程的MDC
                return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
            @Override
            public void execute(Runnable task) {
                super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
        };
        // 其他配置
        executor.initialize();
        return executor;
    }
}
