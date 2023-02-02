package com.kuark.tool.model.concurrents.exceples.example3;

/**
 * Created by caoqingyuan on 2017/6/23.
 */
public class ThreadMain {
    public static void main(String[] s){
        new Thread(new Station("线程1：")).start();
        new Thread(new Station("线程2：")).start();
        new Thread(new Station("线程3：")).start();
        new Thread(new Station("线程4：")).start();
        new Thread(new Station("线程5：")).start();
        new Thread(new Station("线程6：")).start();
        new Thread(new Station("线程7：")).start();
        new Thread(new Station("线程8：")).start();
        new Thread(new Station("线程9：")).start();
        new Thread(new Station("线程10：")).start();
        new Thread(new Station("线程11：")).start();
        new Thread(new Station("线程12：")).start();
    }
}
