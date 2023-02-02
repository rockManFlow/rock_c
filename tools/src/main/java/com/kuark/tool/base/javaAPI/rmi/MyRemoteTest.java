package com.kuark.tool.base.javaAPI.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote 接口用于标识其方法可以从非本地虚拟机上调用的接口。
 * 任何远程对象都必须直接或间接实现此接口。
 * 只有在“远程接口”（扩展 java.rmi.Remote 的接口）中指定的这些方法才可远程使用
 * Created by caoqingyuan on 2017/6/7.
 */
//TODO 远程接口
public interface MyRemoteTest extends Remote{
    /* extends了Remote接口的类或者其他接口中的方法若是声明抛出了RemoteException异常，
 　* 则表明该方法可被客户端远程访问调用。
 　*/
    public String sayHello(String name) throws RemoteException;
}
