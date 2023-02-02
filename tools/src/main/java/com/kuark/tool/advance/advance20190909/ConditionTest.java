package com.kuark.tool.advance.advance20190909;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rock
 * @detail Condition解释:表示对象状态、条件
 * @date 2019/9/9 10:20
 */
public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
//        conditionTest();

        conditionLockTest();
    }
    public static void conditionTest(){
        ReentrantLock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        /**
         * Condition 属于对象监视器，监听对象的状态(wait,notify)，
         * 提供了一种中断当前线程的方法
         * 与当前线程有关，所以，在使用的时候需要先加锁/释放锁
         */
    }

    /***
     * 调用wait方法会原子级释放当前锁对象
     * Condition主要是为了当前锁对象再当前线程中的阻塞状态设计的，使当前线程阻塞
     * Condition是Object对象中wait/notify的替换
     * 就像lock是Sy的替换一样
     * @throws InterruptedException
     */
    public static void conditionLockTest() throws InterruptedException {
        BoundedBuffer buffer=new BoundedBuffer();
    Thread threadA =
        new Thread(
            () -> {
              for (int i = 0; i < 4; i++) {
                try {
                    System.out.println("put start "+i);
                    buffer.put("00" + i);
                    System.out.println("put end "+i);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    threadA.start();

    Thread.sleep(1*1000L);
    threadA.interrupt();
    System.out.println("sleep finish");
        Thread threadB =
                new Thread(
                        () -> {
                            for (int i = 0; i < 4; i++) {
                                try {
                                    System.out.println("get start "+i);
                                    Object take = buffer.take();

                                    System.out.println("get end "+take);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

        threadB.start();
    System.out.println("main end");
    }
}
