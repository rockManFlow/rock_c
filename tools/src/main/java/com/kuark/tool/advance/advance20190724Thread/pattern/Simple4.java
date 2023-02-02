package com.kuark.tool.advance.advance20190724Thread.pattern;

/**
 * 枚举方式是一种天然的单例实现，在项目开发中枚举方式是非常推荐使用的。
 * 它能够保证序列化和反序列化过程中实例的唯一性，而且不用担心线程安全问题
 */
public enum Simple4 {
    SERVICE_A {

        @Override

        protected void hello() {
            System.out.println("hello, service A");
        }
    },

    SERVICE_B {

        @Override

        protected void hello() {

            System.out.println("hello, service B");

        }

    };

    protected abstract void hello();
}
