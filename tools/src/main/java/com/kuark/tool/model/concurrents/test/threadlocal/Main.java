package com.kuark.tool.model.concurrents.test.threadlocal;

/**
 * Created by caoqingyuan on 2017/7/26.
 */
public class Main {
    public static void main(String[] s){
        Constants1.local.set("main");
        System.out.println("main get:"+Constants1.local.get());
        new ThreadLocalB().start();
        new ThreadLocalA().start();
    }
}
