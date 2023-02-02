package com.kuark.tool.base.connects.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

/**
 * Created by caoqingyuan on 2017/10/11.
 */
public class Server {
    public static void main(String[] args){
        System.out.println("start server");
        try {
            MyInterface personService = new MyInterfaceImpl();
            LocateRegistry.createRegistry(8892);
            //实现绑定
            //Naming.rebind("rmi://127.0.0.1:6600/PersonService", personService);  //jdk1.3之前
            Context namingContext = new InitialContext();
            namingContext.rebind("rmi://127.0.0.1:8892/PersonService", personService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end server");
    }
}
