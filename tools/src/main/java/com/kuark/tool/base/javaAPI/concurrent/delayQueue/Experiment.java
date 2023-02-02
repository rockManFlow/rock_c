package com.kuark.tool.base.javaAPI.concurrent.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by caoqingyuan on 2017/3/9.
 */
public class Experiment implements Delayed{
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
