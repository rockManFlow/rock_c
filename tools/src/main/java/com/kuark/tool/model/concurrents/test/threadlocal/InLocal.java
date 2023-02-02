package com.kuark.tool.model.concurrents.test.threadlocal;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class InLocal extends InheritableThreadLocal {
    protected Object initialValue(){
        return "eheheh";
    }
}
