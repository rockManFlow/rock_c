package com.kuark.tool.advance.advance20190724Thread.pool;

/**
 * @author rock
 * @detail
 * @date 2022/2/10 14:08
 */
public class MtUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("uncaughtException="+e.getMessage());
    }
}
