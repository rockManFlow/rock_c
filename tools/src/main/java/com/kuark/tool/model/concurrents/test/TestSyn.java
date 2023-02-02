package com.kuark.tool.model.concurrents.test;

/**
 * Created by caoqingyuan on 2017/7/31.
 */
public class TestSyn {
    private int num;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized int getNum() {
        return num;
    }

    public synchronized void setNum(int num) {
        this.num = num;
    }
}
