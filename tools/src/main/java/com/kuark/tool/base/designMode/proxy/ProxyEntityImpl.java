package com.kuark.tool.base.designMode.proxy;

/**
 * Created by caoqingyuan on 2017/10/17.
 */
public class ProxyEntityImpl implements ProxyEntity {
    @Override
    public void show(){
        System.out.println("ProxyEntity show");
    }

    @Override
    public String writeInfo(String name) {
        System.out.println("writeInfo:"+name);
        return "OK"+name;
    }

    @Override
    public String toString(){
        return "AAAAA";
    }
}
