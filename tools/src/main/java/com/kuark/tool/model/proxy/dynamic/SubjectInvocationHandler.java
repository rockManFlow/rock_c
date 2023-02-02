package com.kuark.tool.model.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实现自己的动态代理类
 * Created by caoqingyuan on 2017/7/5.
 */
public class SubjectInvocationHandler implements InvocationHandler {
    //用于接收具体实现类的对象
    private Object delegate;
    public SubjectInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("proxy="+proxy.hashCode());
        System.out.println("delegate="+delegate.hashCode());
        //经过测试可知，这个方法就是代理的Method对象
        System.out.println("method="+method.getName());
        //利用反射执行某一对象方法的执行
        System.out.println("invoke...");
        method.invoke(delegate, args);//实例对象，该对象需要执行的方法的参数
        System.out.println("invoke end");
        return "ST";
    }
}
