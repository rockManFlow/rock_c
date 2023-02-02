package com.kuark.tool.base.javaAPI.rmi;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 注册远程对象,向客户端提供远程对象服务
 * 远程对象是在远程服务上创建的，你无法确切地知道远程服务器上的对象的名称
 * 但是，将远程对象注册到RMI Service之后，客户端就可以通过RMI Service请求
 * 到该远程服务对象的stub了，利用stub代理就可以访问远程服务对象了
 * Created by caoqingyuan on 2017/6/7.
 */
public class RmiService {
    public static void main(String[] s) throws RemoteException, MalformedURLException {
        //生成stud和skeleton,并返回stub代理引用
        MyRemoteImpl remote=new MyRemoteImpl();
        //注册端口
        LocateRegistry.createRegistry(1099);
        /* 将stub代理绑定到Registry服务的URL上 */
        java.rmi.Naming.rebind("rmi://localhost:1099/hello", remote);
        System.out.print("Ready");
    }
}
