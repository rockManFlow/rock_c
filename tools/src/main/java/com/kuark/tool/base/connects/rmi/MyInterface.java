package com.kuark.tool.base.connects.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 这个接口需要导出到使用的客户端中
 * Created by caoqingyuan on 2017/10/11.
 */
public interface MyInterface extends Remote {
    //1、首先远程方法类必须直接或者间接的实现Remote接口

    //2、接口中的所有方法声明抛出java.rmi.RemoteException
    public String getResults(String name) throws RemoteException;

    //3、创建远程类实现远程接口
}
