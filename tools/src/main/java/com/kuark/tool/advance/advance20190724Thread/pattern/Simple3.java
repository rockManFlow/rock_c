package com.kuark.tool.advance.advance20190724Thread.pattern;

/**
 * 饿汉式--在进行类加载时就进行初始化示例--线程安全
 */
public class Simple3 {
    private static Simple3 instance=new Simple3();
    private Simple3(){}

    public static Simple3 getInstance(){
        return instance;
    }
}
