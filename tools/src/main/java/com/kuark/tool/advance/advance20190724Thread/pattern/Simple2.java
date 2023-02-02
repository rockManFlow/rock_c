package com.kuark.tool.advance.advance20190724Thread.pattern;

/**
 * 单利--
 * 当一个类被加载时，其静态内部类是不会被同时加载的，
 * 只有第一次被调用时才会初始化（才会进行类加载--类加载是线程安全的并且只会加载一次），而且我们不能通过反射的方式获取内部的属性。
 * 由此可见，静态内部类方式实现单例更加安全，可以防止被反射入侵。
 */
public class Simple2 {
    private Simple2(){}

    public static SimpleStance getInstance(){
        return SimpleStance.stance;
    }

    public static class SimpleStance {
        private static SimpleStance stance=new SimpleStance();
    }
}
