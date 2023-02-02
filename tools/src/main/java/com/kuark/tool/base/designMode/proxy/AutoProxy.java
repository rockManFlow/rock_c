package com.kuark.tool.base.designMode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by caoqingyuan on 2017/10/17.
 */
public class AutoProxy implements InvocationHandler {
    //目标类对象实例
    private ProxyEntity target;

    public Object bind(ProxyEntity target) {
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    /**
     *
     * @param proxy 本自动代理实例
     * @param method 被代理接口的调用方法Method类
     * @param args 被代理接口方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(proxy instanceof ProxyEntityImpl){
            System.out.println("是这个ProxyEntity类");
        }
        Object result = null;
        if(proxy instanceof AutoProxy){
            System.out.println("proxy is AutoProxy");
        }


        //执行要处理对象的原本方法
        result=method.invoke(this.target, args);//目标方法
        return result;
    }
}
