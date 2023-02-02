package com.kuark.tool.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/19 17:39
 */
public abstract class AbstractThreadFactory implements ThreadFactory {
    public abstract FutureTask newFutureTask(Callable callable);
}
