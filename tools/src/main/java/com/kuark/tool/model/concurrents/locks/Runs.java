package com.kuark.tool.model.concurrents.locks;

/**
 * Created by caoqingyuan on 2017/7/26.
 */
public class Runs {
    public static void main(String[] s) throws InterruptedException {
        MyServer server=new MyServer();
        WaitNotifyReen a = new WaitNotifyReen(server,"A");
        a.start();
    }
}
