package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.*;

/**
 * @author rock
 * @detail 并发关键字
 * CountDownLatch(同步仅能使用一次)、phaser（阶段同步器）、exchanger（两个同步线程交换数据）、
 * semaphore（信号量控制资源访问的并发数）、CyclicBarrier（循环屏障同步器：同步对象可以重复使用同步）
 * @date 2021/6/9 20:25
 */
public class ConKeyMain {
    public static void main(String[] args) {
        cyclicBarrier();
    }

    private static void phaser(){
        /**
         * Phaser:属于阶段器，等待一组线程完成工作后再执行下一步，协调线程的工作。
         * 它与CountDownLatch和CyclicBarrier类似
         * 基本用法：
         *  通过register()方法注册操作线程数
         *  arriveAndAwaitAdvance() 标记到达并阻塞等待
         *  arriveAndDeregister() 到达并注销该parties，这个方法不会使线程阻塞
         *  getRegisteredParties() 获取当前的parties数
         *  arrive() 到达，但不会使线程阻塞
         *  awaitAdvance() 等待前行，可阻塞也可不阻塞，判断条件为传入的phase是否为当前phaser的phase。如果相等则阻塞，反之不进行阻塞---重写或使用onAdvance方法
         *  ......
         */
        class MyPhase extends Phaser {

            /**
             * 重写该方法 实现自定义逻辑 返回true：该阶段器就会停止。返回false会继续执行
             * @param phase 阶段
             * @param registeredParties 到达的线程数
             * @return
             */
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                // 每到达一个阶段 都会触发当前方法  每一个阶段phase都会加1 从0开始
                // 返回值  true 表示结束所有几段， false表示继续
                switch (phase) {
                    case 0:
                        // 游戏准备阶段
                        prepareStage();
                        return false;
                    case 1:
                        // 第一关
                        firstStage();
                        return false;
                    case 2:
                        // 第二关
                        secondStage();
                        return false;
                    case 3:
                        // 第三关 完成 结束比赛
                        thirdStage();
                        return true;
                    default:
                        return true;
                }
            }

            void prepareStage() {
                System.out.println("所有人已准备完成，游戏准备开始，参加游戏人数：" +  getRegisteredParties());
            }

            void firstStage() {
                System.out.println("所有人已完成第一关，即将开始第二关，已就位人数：" + getRegisteredParties());
            }

            void secondStage() {
                System.out.println("所有人已完成第二关，即将开始第三关，已就位人数：" + getRegisteredParties());
            }

            void thirdStage() {
                System.out.println("所有人已完成第三关，游戏结束，完成人数：" + getRegisteredParties());
                System.out.println("此次游戏圆满结束，嫂子们一人发给小明一张好人卡...");
            }
        }

        Phaser phaser = new MyPhase();
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(() -> {
                // 调用该方法表示 到达某一阶段 并等待其他线程到达
                System.out.println(Thread.currentThread().getName() + "开始准备...");
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + "开始第一关...");
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + "开始第二关...");
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + "开始第三关...");
                phaser.arriveAndAwaitAdvance();
            });
            thread.setName("玩家" + (i + 1));
            // 注册线程个数
            phaser.register();
            thread.start();
        }
    }

    private static void exchanger(){
        /**
         * Exchanger：用于两个线程信息的交换
         * 1、此程序创建了两个线程，线程在执行过程中，调用同一个exchanger对象的exchange方法，进行信息通信，
         * 当两个线程均已将信息放入到exchanger对象中时，exchanger对象会将两个线程放入的信息交换，然后返回。
         * 2、对于多个线程同时调用exchanger，是随机进行交换的。当是奇数时，剩余的一个会一直阻塞，直到匹配的线程到达才会继续执行
         */

        class ThreadAb implements Runnable{
            private Exchanger<String> exchanger;
            private String name;
            private String threadName;
            public ThreadAb(Exchanger exchanger,String name,String threadName){
                this.exchanger=exchanger;
                this.name=name;
                this.threadName=threadName;
            }

            @Override
            public void run() {
                try {
                    System.out.println("run :"+threadName+"|result:"+exchanger.exchange(name));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Exchanger exchanger=new Exchanger();
        new Thread(new ThreadAb(exchanger,"re111","threadName1")).start();
        new Thread(new ThreadAb(exchanger,"re222","threadName2")).start();
        //run :threadName1|result:re222
        //run :threadName2|result:re111
    }

    private static void semaphore(){
        /**
         * Semaphore:信号量，也是一个同步装置，控制同时访问资源的线程个数
         * Semaphore 的主要方法摘要：
         *
         * 　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
         *
         * 　　void release():释放一个许可，将其返回给信号量。
         *
         * 　　int availablePermits():返回此信号量中当前可用的许可数。
         *
         * 　　boolean hasQueuedThreads():查询是否有线程正在等待获取。
         */
        class ThreadA implements Runnable{
            private Semaphore semaphore;
            private int num;
            public ThreadA(Semaphore semaphore,int num){
                this.semaphore=semaphore;
                this.num=num;
            }
            @Override
            public void run() {
                try {
                    System.out.println("等待获取信号量："+num);
                    semaphore.acquire();
                    System.out.println("获取信号量："+num);
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        }



        Semaphore semaphore=new Semaphore(2);
        for(int i=0;i<10;i++){
            new Thread(new ThreadA(semaphore,i)).start();
        }


    }


    /**
     * CyclicBarrier可以循环使用的屏障(同步器)：一个await当指定线程调用次数达到指定值，会放行继续执行代码。
     * 冲破屏障之后，这个屏障对象可再次使用
     * 可以用于多线程计算数据，最后合并计算结果的场景。
     */
    private static void cyclicBarrier(){
        class TaskThread extends Thread {

            CyclicBarrier barrier;

            public TaskThread(CyclicBarrier barrier) {
                this.barrier = barrier;
            }

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(getName() + " 到达栅栏 A");
                    barrier.await();
                    System.out.println(getName() + " 冲破栅栏 A");

                    Thread.sleep(2000);
                    System.out.println(getName() + " 到达栅栏 B");
                    barrier.await();//线程调用 await() 表示自己已经到达栅栏
                    System.out.println(getName() + " 冲破栅栏 B");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //可以循环使用：一个await当指定线程调用次数达到指定值，会放行继续执行代码。冲破屏障之后，这个屏障会再次可用
        CyclicBarrier cb=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程已同步完成");
            }
        });

        for(int i = 0; i < 5; i++) {
            new TaskThread(cb).start();
        }
    }
}
