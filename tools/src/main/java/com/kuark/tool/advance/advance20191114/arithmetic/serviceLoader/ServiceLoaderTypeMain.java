package com.kuark.tool.advance.advance20191114.arithmetic.serviceLoader;

import java.util.*;

/**
 * @author rock
 * @detail jdk源码类 ServiceLoader 类的学习
 * 动态配置接口实现类的配置
 * @date 2019/11/20 14:52
 */
public class ServiceLoaderTypeMain {
    /**
     * todo ServiceLoader这个类是jdk提供的动态配置实现指定接口的实现类---工具类
     * 不用加载所有的实现类再筛选，可以通过配置加载指定的实现类对象
     * 需要在resources目录下创建从META-INF/services这个目录下的配置文件加载给定接口或者基类的实现
     * 文件名为接口名的全县命名，内容为实现类的全线命名
     * @param args
     */
    public static void main(String[] args){
        ServiceLoader<PayInter> serviceLoader=ServiceLoader.load(PayInter.class);
        Iterator<PayInter> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            PayInter next = iterator.next();
            System.out.println(next.show());
        }

    }

    public static void delCollection(){

    }
}
