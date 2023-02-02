package com.kuark.tool.base.javaAPI.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程接口实现类
 * 远程对象必须实现java.rmi.server.UniCastRemoteObject类，这样才能保证客户端访问获得远程对象时，
 * 该远程对象将会把自身的一个拷贝以Socket的形式传输给客户端，此时客户端所获得的这个拷贝称为“存根”，
 * 而服务器端本身已存在的远程对象则称之为“骨架”。其实此时的存根是客户端的一个代理，用于与服务器端的通信，
 * 而骨架也可认为是服务器端的一个代理，用于接收客户端的请求之后调用远程方法来响应客户端的请求。
 * Created by caoqingyuan on 2017/6/7.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemoteTest{
    //服务器端获取这个UniCastRemoteObject类就可以动态生成stub(存在于客户端来和server进行交互)和skeleton(存在于服务端来和client进行交互)代理
    public MyRemoteImpl()throws RemoteException{
        super();
    }
    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello "+name+" 访问";
    }
}
