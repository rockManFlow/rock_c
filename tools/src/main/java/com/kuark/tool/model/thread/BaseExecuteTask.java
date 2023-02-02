package com.kuark.tool.model.thread;

/**
 * Created by caoqingyuan on 2016/9/9.
 */
public class BaseExecuteTask extends ExecuteTask {
    private String sendMes;
    @Override
    public void run() {
        System.out.println("sendMes="+sendMes);
    }
    public void work(String mes){
        sendMes=mes;
    }
}
