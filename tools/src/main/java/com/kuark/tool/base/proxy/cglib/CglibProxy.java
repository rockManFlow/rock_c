package com.kuark.tool.base.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author caoqingyuan
 * @detail 使用CGLIB实现动态代理
 * @date 2019/5/21 19:26
 */
public class CglibProxy implements MethodInterceptor {
    private Object targetObject;

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        //这是生成一个子类，继承于传进来的父类型
        Object proxyObj = enhancer.create();
        return proxyObj;
    }

    /**
     * 再执行每个方法之前都会调用先调用这个方法，通过这个方法来执行具体方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //前方法
//        Object obj = method.invoke(targetObject, objects);
        //通过代理类调用父类中的方法
        Object obj =methodProxy.invokeSuper(o,args);
        //后方法
        return obj;
    }

    public static void main(String[] args) {
        UserManagerImpl userManagerImpl=new UserManagerImpl();
        UserManager userManager = (UserManager) new CglibProxy().createProxyObject(userManagerImpl);
        System.out.println("CGLibProxy：");
        userManager.addUser("tom", "root");
    }

    interface UserManager{
        void addUser(String name,String desc);
    }

    public static class UserManagerImpl implements UserManager{

        public UserManagerImpl(){}
        @Override
        public void addUser(String name, String desc) {

        }
    }
}
