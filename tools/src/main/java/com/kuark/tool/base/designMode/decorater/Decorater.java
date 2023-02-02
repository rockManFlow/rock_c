package com.kuark.tool.base.designMode.decorater;

/**
 * Created by caoqingyuan on 2017/6/1.
 */
public abstract class Decorater implements Compent {
    protected Compent compent;
    public Decorater(Compent compent){
        super();
        this.compent=compent;
    }
    public void method(){
        compent.method();
    }
}
