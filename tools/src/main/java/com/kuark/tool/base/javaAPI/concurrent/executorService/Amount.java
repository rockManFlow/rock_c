package com.kuark.tool.base.javaAPI.concurrent.executorService;

/**
 * Created by caoqingyuan on 2016/8/12.
 */
public class Amount {
    public String name;
    public int money;
    public Amount(String name,int money){
        this.name=name;
        this.money=money;
    }
    public synchronized int getMoney(int mon) throws InterruptedException {
        int n=money-mon;
        Thread.sleep(10);
        money=n;
        return money;
    }
    public synchronized void income(int mon) throws InterruptedException {
        int n=money+mon;
        Thread.sleep(10);
        money=n;
    }
    public void test(){
        Integer a=0;
        synchronized (a){
            System.out.println("sssss");
        }
    }
}
