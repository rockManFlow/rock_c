package com.kuark.tool.model.concurrents.timer;

import java.util.TimerTask;

/**
 * Created by caoqingyuan on 2017/7/27.
 */
public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("myTask run");
    }
}
