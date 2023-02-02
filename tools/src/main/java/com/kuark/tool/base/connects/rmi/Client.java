package com.kuark.tool.base.connects.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Created by caoqingyuan on 2017/10/11.
 */
public class Client {
    public static void main(String[] args){
        try {
            Context namingContext = new InitialContext();// 初始化命名内容
            MyInterface RmObj2 = (MyInterface) namingContext
                    .lookup("rmi://localhost:8892/PersonService");//获得远程对象的存根对象
            System.out.println(RmObj2.getResults("HH"));//通过远程对象，调用doSomething方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
