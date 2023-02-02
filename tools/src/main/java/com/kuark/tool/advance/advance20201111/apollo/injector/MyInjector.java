package com.kuark.tool.advance.advance20201111.apollo.injector;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author rock
 * @detail  Guice作为IOC容器
         * Guice：整个框架的门面
         * Injector：一个依赖的管理上下文
         * Binder：一个接口和实现的绑定
         * Module：一组Binder
         * Provider：bean的提供者
         * Key：Binder中对应一个Provider
         * Scope：Provider的作用域
         * Stage：运行方式（为了不同的要求）
 * @date 2021/4/1 14:11
 */
public class MyInjector {
    private static Injector s_injector;
    private static final Object lock=new Object();

    public static Injector getInjector(){
        if(s_injector==null){
            synchronized (lock){
                if(s_injector==null){
                    s_injector= Guice.createInjector(new MyModule());
                }
            }
        }
        return s_injector;
    }

    public static void main(String[] args) {
        WriteInterface instance = getInjector().getInstance(WriteInterface.class);
        instance.write("xxxxx");
    }
}
