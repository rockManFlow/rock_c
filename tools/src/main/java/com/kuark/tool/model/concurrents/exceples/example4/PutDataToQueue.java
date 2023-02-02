package com.kuark.tool.model.concurrents.exceples.example4;



import com.kuark.tool.model.concurrents.exceples.example4.interfaces.ServiceInter;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by caoqingyuan on 2017/6/26.
 */
public class PutDataToQueue implements ServiceInter {
    public static final ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();

    @Override
    public void service(String data) {
        handle(data);
    }
    private String[] handle(String infos){
        String[] st=infos.split("\\r\\n");
        System.out.println("size="+st.length);
        return st;
    }
}
