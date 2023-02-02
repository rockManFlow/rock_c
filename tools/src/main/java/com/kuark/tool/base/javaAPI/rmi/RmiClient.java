package com.kuark.tool.base.javaAPI.rmi;

import java.rmi.Naming;

/**
 * Created by caoqingyuan on 2017/6/7.
 */
public class RmiClient {
    public static void main(String[] args) {
        try {
            /* 从RMI Registry中请求stub
             * 如果RMI Service就在本地机器上，URL就是：rmi://localhost:1099/hello
             * 否则，URL就是：rmi://RMIService_IP:1099/hello
             */
            MyRemoteTest hello = (MyRemoteTest) Naming.lookup("rmi://localhost:1099/hello");
            /* 通过stub调用远程接口实现 */
            System.out.println(hello.sayHello("zhangxianxin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
