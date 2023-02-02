package com.kuark.tool.advance.advance20190724Thread.pattern;

/**
 * 懒汉式:双层验证--线程安全
 */
public class Simple1 {
    private static Simple1 instance=null;

    //私有构造
    private Simple1(){}

    public static Simple1 getInstance(){
        if(instance==null){
            synchronized (Simple1.class){
                if(instance==null){
                    instance=new Simple1();
                }
            }
        }
        return instance;
    }
}
