package com.kuark.tool.model.concurrents.exceples.example2;

/**
 * Created by caoqingyuan on 2017/6/22.
 */
public class ThreadMain {
    public static void main(String[] s){
        System.out.println("main start");
        for(int i=0;i<3;i++){
            Contants.writePool.submit(new FileWriterThread());
        }
        long startTime=System.currentTimeMillis();
        for(int i=0;i<10;i++){
            Porter.service("AAAABBBB"+i);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("submit data time:"+(endTime-startTime)+"ms");
        System.out.println("main end");
    }
}
