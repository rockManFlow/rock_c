package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author caoqingyuan
 * @detail 多个线程分别对同一个对象执行操作多少次
 * 多线程同步
 * @date 2019/7/24 19:20
 */
public class CountMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        futureAndCountDown();
    }

    /**
     * 多线程同步执行，都完成后，汇总结果
     * @throws InterruptedException
     */
    public static void futureAndCountDown() throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch=new CountDownLatch(3);
        Future submit = exec.submit(new CountDownTask2("name" + 1, latch));

        Future submit2 = exec.submit(new CountDownTask2("name" + 2, latch));

        Future submit3 = exec.submit(new CountDownTask2("name" + 3, latch));

        latch.await();

        String result1 = (String)submit.get();
        String result2 = (String)submit2.get();
        String result3 = (String)submit3.get();
        System.out.println("result1="+result1);
        System.out.println("result2="+result2);
        System.out.println("result3="+result3);
    }

    /**
     * AtomicLong原子自增保证安全性
     * @throws InterruptedException
     */
    public static void signalCountDown() throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(100);
        AtomicLong atomicLong=new AtomicLong(0);
        for(int i=0;i<100;i++){
            new Thread(new CountTask(atomicLong,latch)).start();
        }
        latch.await();
        System.out.println("result="+atomicLong);
    }

    /**
     * 由于竞争很激烈，这样的 flush 和 refresh 操作耗费了很多资源，而且 CAS 也会经常失败
     * 高并发下 LongAdder 比 AtomicLong 效率更高
     *
     * 因为 LongAdder 引入了分段累加的概念，内部一共有两个参数参与计数：第一个叫作 base，它是一个变量，第二个是 Cell[] ，是一个数组。
     * 竞争激烈的时候，LongAdder 会通过计算出每个线程的 hash 值来给线程分配到不同的 Cell 上去，每个 Cell 相当于是一个独立的计数器，
     * 这样一来就不会和其他的计数器干扰，Cell 之间并不存在竞争关系，所以在自加的过程中，就大大减少了刚才的 flush 和 refresh，
     * 以及降低了冲突的概率，这就是为什么 LongAdder 的吞吐量比 AtomicLong 大的原因，本质是空间换时间，
     * 因为它有多个计数器同时在工作，所以占用的内存也要相对更大一些。
     *
     * 执行 LongAdder.sum() 的时候，会把各个线程里的 Cell 累计求和，并加上 base，形成最终的总和
     */
    public static void londAdderAdd() throws InterruptedException {
        LongAdder counter = new LongAdder();

        ExecutorService service = Executors.newFixedThreadPool(16);

        for (int i = 0; i < 100; i++) {

            service.submit(new Task(counter));

        }

        Thread.sleep(2000);

        System.out.println(counter.sum());
    }

    static class Task implements Runnable {

        private final LongAdder counter;

        public Task(LongAdder counter) {

            this.counter = counter;

        }

        @Override

        public void run() {

            counter.increment();

        }

    }
}
