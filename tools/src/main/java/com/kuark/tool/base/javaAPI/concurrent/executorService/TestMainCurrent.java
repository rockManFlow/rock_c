package com.kuark.tool.base.javaAPI.concurrent.executorService;

/**
 *TODO 测试主线程中，如果其中的子线程没有执行完，主线程已经执行完了，子线程还会不会执行？？
 * 结论：main函数会等在子线程执行完，但junit测试体不会
 * Created by caoqingyuan on 2017/2/28.
 */
public class TestMainCurrent {
    public static void main(String[] args){
        System.out.println("main start");
        System.out.println("main end");
    }
}
