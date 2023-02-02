package com.kuark.tool.model.concurrents.exceples.example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by caoqingyuan on 2017/6/22.
 */
public class Contants {
    public static final LinkedBlockingQueue queue=new LinkedBlockingQueue(2000);
    public static final ExecutorService pool= Executors.newFixedThreadPool(3);//10线程1秒25万 30线程1秒21万
    public static final ExecutorService writePool= Executors.newFixedThreadPool(1);
}
