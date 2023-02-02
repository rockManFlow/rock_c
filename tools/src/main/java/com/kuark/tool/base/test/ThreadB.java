package com.kuark.tool.base.test;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/12/28 10:12
 */
public class ThreadB extends Thread {
    private ThreadVo vo;
    ThreadB(ThreadVo vo){
        this.vo=vo;
    }
    @Override
    public void run() {
        System.out.println("start ThreadB run");
        try {
            this.vo.show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
