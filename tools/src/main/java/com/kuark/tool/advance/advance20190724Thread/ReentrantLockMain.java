package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author rock
 * @detail
 * @date 2021/6/10 17:07
 */
public class ReentrantLockMain {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        for(int i=0;i<4;i++){
            if(i<2){
                new Thread(new ThreadReadA(readLock,"read"+i)).start();
            }else {
                new Thread(new ThreadWriteA(writeLock,"write"+i)).start();
            }
        }
    }

    static class ThreadReadA implements Runnable{
        private ReentrantReadWriteLock.ReadLock readLock;
        private String name;
        public ThreadReadA(ReentrantReadWriteLock.ReadLock readLock,String name){
            this.readLock=readLock;
            this.name=name;
        }
        @Override
        public void run() {
            System.out.println("readThread:"+name+" start lock...");
            readLock.lock();
            System.out.println("readThread:"+name+" get lock");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("readThread:"+name+" release lock");
                readLock.unlock();
            }
        }
    }

    static class ThreadWriteA implements Runnable{
        private ReentrantReadWriteLock.WriteLock writeLock;
        private String name;
        public ThreadWriteA(ReentrantReadWriteLock.WriteLock writeLock,String name){
            this.writeLock=writeLock;
            this.name=name;
        }
        @Override
        public void run() {
            System.out.println("writeThread:"+name+" start lock...");
            writeLock.lock();
            System.out.println("writeThread:"+name+" get lock");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("writeThread:"+name+" release lock");
                writeLock.unlock();
            }
        }
    }
}
