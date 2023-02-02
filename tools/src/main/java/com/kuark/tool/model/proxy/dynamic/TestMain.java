package com.kuark.tool.model.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by caoqingyuan on 2017/7/5.
 */
public class TestMain {
    public static void main(String[] s){
        //具体实现类
        Parente parente=new RealParent();
        //代理类
        InvocationHandler invocationHandler=new SubjectInvocationHandler(parente);
        //TODO 中间不能有抽象类--不然转换异常
        Parente reals=(Parente) Proxy.newProxyInstance(Parente.class.getClassLoader(),new Class[]{Parente.class},invocationHandler);
        reals.eat("梨");
        reals.drink("水");
    }
}
