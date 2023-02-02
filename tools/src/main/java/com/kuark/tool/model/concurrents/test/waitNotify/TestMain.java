package com.kuark.tool.model.concurrents.test.waitNotify;


import java.io.IOException;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class TestMain {
    public static Integer i=0;
    public static void main(String[] s) throws InterruptedException, IOException {
//        String lock=new String("lock");
        //一个生产者多个消费者---避免假死状态使用notifyAll来避免
//        new Thread(new Product1(lock,"A")).start();
//        new Thread(new Consume1(lock,"B")).start();
//        new Thread(new Consume1(lock,"C")).start();
        //管道通信
//        PipedOutputStream out=new PipedOutputStream();//字节流
//        PipedInputStream read=new PipedInputStream();
        //字符流
//        PipedReader reader=new PipedReader();
//        PipedWriter writer=new PipedWriter();
//        out.connect(read);//输入输出管道必须连接上才能一个生产一个消费
//        new Thread(new PipedReadData(read)).start();
        //先启动读取管道，由于没有数据导致程序阻塞
//        Thread.sleep(2000);
//        new Thread(new PipedWriteData(out)).start();
        //测试join方法，使当前线程阻塞直到所属线程执行完毕
//        Thread join = new Thread(new JoinThread());
//        Thread join2 = new Thread(new JoinThread2(join));
//        join2.start();
//        join.start();
//        join.join();
        //ThreadLocal类的使用
//        ThreadLocalB b=new ThreadLocalB();
//        System.out.println("main local:"+ Constants1.inLocal.get());
//        Thread.sleep(2000);
//        ThreadLocalA a=new ThreadLocalA();
////        b.start();
//        a.start();
    }
}
