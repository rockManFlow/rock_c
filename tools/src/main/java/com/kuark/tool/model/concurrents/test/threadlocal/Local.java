package com.kuark.tool.model.concurrents.test.threadlocal;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class Local extends ThreadLocal {
    //继承ThreadLocal重写initialValue方法，使第一次get获取非空
    protected Object initialValue(){
        return "no";
    }
}
