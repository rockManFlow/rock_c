package com.kuark.tool.base.connects.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程类--必须实现远程接口
 * 为使远程类能导出为远程对象
 * Created by caoqingyuan on 2017/10/11.
 */
public class MyInterfaceImpl extends UnicastRemoteObject implements MyInterface {
    //用于创建存根和骨架
    public MyInterfaceImpl() throws RemoteException {
    }

    //1、远程类实现远程接口的同时继承java.rmi.server.UnicastRemoteObject类
    //远程类的构造方法必须声明抛出RemoteException---最常用的
    @Override
    public String getResults(String name) throws RemoteException {
        System.out.println("name:"+name);
        return "OK"+name;
    }
}
