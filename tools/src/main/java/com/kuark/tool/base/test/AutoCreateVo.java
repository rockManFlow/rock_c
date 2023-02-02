package com.kuark.tool.base.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by cc on 2016/5/9.
 * 连接数据库中的表，自动生成实体
 */
public class AutoCreateVo {
    //对象创建流程
    // 1、类加载：类校验等
    // 2、初始化执行类构造器（1、父类静态变量 2.父类静态代码块 3.子类静态变量 4.子类静态代码块 5.父类变量 6.父类代码块 7.父类构造方法 8.子类变量 9.子类代码块 10.子类构造器）
    private static String aa="sss";
    static {
        System.out.println("sss");
    }
    private Integer age;
    {
        System.out.println("父类代码块");
    }
    private AutoCreateVo(){
        System.out.println("父类构造方法");
    }
}
