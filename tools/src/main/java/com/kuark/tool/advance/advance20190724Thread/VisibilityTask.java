package com.kuark.tool.advance.advance20190724Thread;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail 线程可见性理解
 * 静态变量非线程安全，但本身具有可见性
 * @date 2019/10/3 9:35
 */
public class VisibilityTask {
    private static volatile Boolean isOver=false;

    private static Integer num=1;

    /**
     * 每次加锁和释放锁之前，相同监视器对象中的共享变量都会被强制进行与主内存中的数据同步刷新
     * 这就是JMM（Java内存模型）规定的
     * 所以锁关键字不仅有互斥作用，还有可见性作用
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        lockNoVisibility();
    }

    public static void lockNoVisibility() throws InterruptedException {
        //锁是保证的锁对象的可见性，互斥性
        String lock="lock";
//        Integer num=1;
        EntityA a=new VisibilityTask().new EntityA();
        a.setAge(20);
        a.setName("xiaohong");
        Thread thread =
                new Thread(
                        () -> {
                            a.setName("hhhh");
                            a.setAge(10);
                            synchronized (lock){
                                System.out.println("run before="+a);
                                a.setName("ssss:"+a.getName());
                                a.setAge(a.getAge());
                                try {
                                    TimeUnit.MILLISECONDS.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                a.setAge(a.getAge()+5);
                                System.out.println("run end="+a);
                            }
                        });
        thread.start();

        String lock2="bbbb11";
        Thread thread2 =
                new Thread(
                        () -> {
                            System.out.println("run2 before="+a);
                            synchronized (lock2){
                                System.out.println("run2 before2="+a);
                                try {
                                    TimeUnit.MILLISECONDS.sleep(150);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                a.setName("de222");
                                a.setAge(a.getAge()+10);
                                System.out.println("run2 end="+a);
                            }
                        });
        thread2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("visibility end="+a);
    }

    public static void visibility() throws InterruptedException {
        //由于可见性问题，可能会导致run end永远不会被执行
        Thread thread =
                new Thread(
                        () -> {
                            while (!isOver) {
//                                Thread.yield();
                                System.out.println("ccc");
                            }
                            System.out.println("run end");
                        });
        thread.start();
        Thread.sleep(112);
        isOver=true;
        System.out.println("visibility end");
    }

    public static void lockVisibility() throws InterruptedException {
        Thread thread =
                new Thread(
                        () -> {
                            synchronized (VisibilityTask.class) {
                                while (!isOver) {
//                                    System.out.println("ccc");
                                }
                                System.out.println("run end");
                            }
                        });
        thread.start();
        Thread.sleep(150);
        isOver=true;
        System.out.println("end");
    }

    public static void show(final int a){
        System.out.println("a="+a);
    }

    @Data
    public class EntityA{
        private String name;
        private Integer age;
    }

    /**
     * volatile：由内存屏障来实现（更新强制刷新到主存中）
     * 锁特点：共享变量在获取锁前强制清空线程共享内存值从主从中拉取(会有加锁前线程修改了值但还没刷新到主存，当加锁时强制清空会有问题)+释放锁时会强制刷新值到主存中
     * final如何保证可见性
     * 一个对象中的所有final参数必须先初始化完之后，才能被使用--如何保证的
     * 内存屏障作用：防止一些指令重排序（及必须先执行及后执行）+强制线程中的数据必须刷新到主内存中
     */
}
