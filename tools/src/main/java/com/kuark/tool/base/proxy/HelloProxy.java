package com.kuark.tool.base.proxy;

/**
 * @author rock
 * @detail ¾²Ì¬´úÀí
 * @date 2021/7/14 15:16
 */
public class HelloProxy implements HelloInterface {
    private HelloInterface hs=new HelloInterfaceImpl();
    @Override
    public void show() {
        hs.show();
    }

    public static void main(String[] args) {
        HelloInterface hs=new HelloProxy();
        hs.show();
    }
}
