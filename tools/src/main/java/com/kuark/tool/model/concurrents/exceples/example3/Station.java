package com.kuark.tool.model.concurrents.exceples.example3;

/**
 * Created by caoqingyuan on 2017/6/23.
 */
public class Station implements Runnable {
    public static Integer num=900;//保证变量是最新的数值
    public static Object ob=new Object();
    private String name;
    public Station(String name){
        this.name=name;
    }

    @Override
    public void run() {
        while (true) {
            boolean b=service();
            if(!b){
                break;
            }
        }
    }

    //TODO 问题：当锁对象为num时会出现数据不一致的情况，当为另外一个锁对象时还没有测试出有问题的，还得再试
    private boolean service(){
        //记录的为是否获取到一张票
        boolean isGet=false;
        int currentNum=-1;
        synchronized (ob) {//当锁对象为num时会出现数据不一致的情况，当为另外一个锁对象时还没有测试出有问题的，还得再试
            if(num>0) {
                isGet=true;
                currentNum=num;
                --num;
                System.out.println(name+"当前剩余票数：【"+num+"】");
            }
        }
        if (isGet) {
            boolean sell = sell(currentNum);
            if (!sell) {
                synchronized (ob) {//票回库
                    ++num;
                }
            }else{
                Utils.writeDataIntoFile(name+"SELL SUCCESSED");
            }
            return true;
        }else{
            System.out.println(name+"没有获取到可售的票，则退出当前线程循环");
            return false;
        }
    }

    private boolean sell(int currentNum) {
        try {
            Thread.sleep(2 * 1000);//表示业务逻辑处理时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (currentNum % 5 == 0) {//为5的倍数不出售
            return false;
        }
        return true;
    }
}
