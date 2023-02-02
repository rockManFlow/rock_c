package com.kuark.tool.model.thread;

import java.sql.SQLException;

/**
 * Created by caoqingyuan on 2016/12/6.
 */
public class MainBase {
    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BasePojo p=new BasePojo();
                p.setAge(1222);
                p.setName("paa1");
                System.out.println("run1="+p.getAge());
                try {
//                    Thread.sleep(3*1000);
                    BaseVar.holdDbConn(p,p.getAge());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    BaseVar.unHoldDbConn();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BasePojo p=new BasePojo();
                p.setAge(11);
                p.setName("p1");
                System.out.println("run1="+p.getAge());
                try {
                    BaseVar.holdDbConn(p,p.getAge());
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    BaseVar.unHoldDbConn();
                }
            }
        }).start();
    }
}
