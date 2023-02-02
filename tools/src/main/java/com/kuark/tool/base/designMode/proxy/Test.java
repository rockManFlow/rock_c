package com.kuark.tool.base.designMode.proxy;

/**
 * Created by caoqingyuan on 2017/10/17.
 */
public class Test {
    public static void main(String[] args){
        ProxyEntity hello = (ProxyEntity) new AutoProxy().bind(new ProxyEntityImpl());
        hello.show();
        String info = hello.writeInfo("xiaohong");
        System.out.println("info:"+info);
    }
}
