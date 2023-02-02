package com.kuark.tool.advance.advance20190724Thread;

import org.springframework.core.NamedThreadLocal;

/**
 * @author rock
 * @detail InheritableThreadLocal 特点
 * @date 2020/11/20 15:02
 */
public class ThreadLocalMain {
    public static void main(String[] args) {
        ThreadLocal local=new ThreadLocal();
        local.set("11111");

        //子线程可继承父线程的上下文信息
        final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>();
        inheritableThreadLocal.set("fatherName");
        new Thread(new Runnable() {
            public void run() {
                //从子线程中可以获取主线程上下文信息
                String childThreadName = inheritableThreadLocal.get();
                System.out.println("childThreadName1 is:"+childThreadName);

                //这种方式是获取不到父上下文信息的，因为这个方法中是实时获取当前线程上下文的Thread t = Thread.currentThread();
                String f=(String)local.get();
                System.out.println("local f:"+f);//获取是null

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String childThreadName = inheritableThreadLocal.get();
                        System.out.println("childThreadName11 is:"+childThreadName);
                    }
                }).start();
            } }).start();

        //属于spring的东西---仅加了个名字
        NamedThreadLocal<Integer> namedThreadLocal=new NamedThreadLocal<Integer>("name-thread-local");
    }
}
