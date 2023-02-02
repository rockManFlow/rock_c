package com.kuark.tool.model.concurrents.exceples.example5;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class DbSinumate {
    public static void writeDB(String dbName,String name){
        try {
            Thread.sleep(500);
            System.out.println(name+"线程向数据库"+dbName+"插入数据完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
