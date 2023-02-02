package com.kuark.tool.model.concurrents.exceples.example2;

/**
 * Created by caoqingyuan on 2017/6/22.
 */
public class Porter {
    public static void service(String data){
        Contants.pool.submit(new ThreadA(data));
    }
}
