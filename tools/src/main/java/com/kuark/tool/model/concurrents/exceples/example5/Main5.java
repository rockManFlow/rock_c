package com.kuark.tool.model.concurrents.exceples.example5;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class Main5 {
    public static void main(String[] s){
        String lock="lock";
        new Thread(new A(lock,"A1")).start();
        new Thread(new A(lock,"A2")).start();
        new Thread(new A(lock,"A3")).start();
        new Thread(new A(lock,"A4")).start();
        new Thread(new A(lock,"A5")).start();
        new Thread(new A(lock,"A6")).start();
        new Thread(new A(lock,"A7")).start();
        new Thread(new A(lock,"A8")).start();
        new Thread(new A(lock,"A9")).start();
        new Thread(new A(lock,"A10")).start();

        new Thread(new B(lock,"B1")).start();
        new Thread(new B(lock,"B2")).start();
        new Thread(new B(lock,"B3")).start();
        new Thread(new B(lock,"B4")).start();
        new Thread(new B(lock,"B5")).start();
        new Thread(new B(lock,"B6")).start();
        new Thread(new B(lock,"B7")).start();
        new Thread(new B(lock,"B8")).start();
        new Thread(new B(lock,"B9")).start();
        new Thread(new B(lock,"B10")).start();
    }
}
