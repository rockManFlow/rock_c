package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author rock
 * @detail FutureTask实现了Runnable，可以获取线程执行结果并来控制线程是否取消等
 * @date 2019/9/17 11:21
 *
 * 线程阻塞方法：阻塞不会释放CPU时间片
 *  sleep(阻塞)、wait（notify唤醒，阻塞）、join（等待其他线程终止，阻塞）
 *  中断、超时、执行完
 *
 *  、yield（线程礼让，让出CPU执行片，不属于阻塞,线程变成就绪状态）
 */
@Slf4j
public class FutureTaskMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        runTask();
    }

    private static void futureTask1() throws InterruptedException, ExecutionException {
        /**
         * 实现Runnable接口，并在FutureTask类内部有Callable参数会把Runnable+Callable等实现赋值到该参数（Runnable会适配成callable），
         * 在run方法中执行callable.call方法
         */
        FutureTask futureTask=new FutureTask(new CallableTask(3));
        new Thread(futureTask).start();

        while (true){
            if(futureTask.isDone()){
                log.info("result="+futureTask.get());
                break;
            }
            log.info("wait...");
        }
    }

    private static void runTask() throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask futureTask=new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("run call");
                return "OK";
            }
        });

        Thread thread=null;
        try {
            thread=new Thread(futureTask);
            thread.start();
//            Object result = futureTask.get(3, TimeUnit.SECONDS);
//            System.out.println("run result="+result);
        }finally {
            //中断仅是向线程发送一个中断标识，至于该标识线程是否响应，需要线程处理
            thread.interrupt();
        }
    }

    /**
     * thread.stop()方法来停止线程，但已经被标记为@Deprecated（弃用），
     * 因为突然停止一个正在运行或挂起的线程是非常危险的，就像突然断电一样，程序还没执行完毕就突然关闭了.
     *
     * 停止一个线程
     * 使线程获取中断标识，并退出程序
     * 使用停止位，使用volatile关键字修饰（从主内存中来直接获取值）
     *
     * volatile：每次读取volatile变量，都应该从主存读取，而不是从CPU缓存读取。每次写入一个volatile变量，应该写到主存中，而不是仅仅写到CPU缓存。
     *
     * volatile特点：
     * 可以将对volatile变量的读写理解为一个触发刷新的操作，写入volatile变量时，线程中的所有变量也都会触发写入主存。而读取volatile变量时，
     * 也同样会触发线程中所有变量从主存中重新读取。因此，应当尽量将volatile的写入操作放在最后，
     * 这样就能连带将其他变量也进行刷新。上面的例子中，update()方法对days的赋值就是放在years、months之后，
     * 就是保证years、months也能将最新的值写入到主存，如果是放在两个变量之前，则days会写入主存，而years、months则不会。
     *
     * 但有时会发生指令重排序，虽然volatile的写入操作放在最后，但发生指令重排序时，就不一定了
     */
}
