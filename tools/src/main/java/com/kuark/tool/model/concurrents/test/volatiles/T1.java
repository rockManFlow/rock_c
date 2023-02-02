package com.kuark.tool.model.concurrents.test.volatiles;

/**
 * Created by caoqingyuan on 2017/7/24.
 */
public class T1 implements Runnable {
    private Boolean isAc=true;

    public Boolean getAc() {
        return isAc;
    }

    public void setAc(Boolean ac) {
        isAc = ac;
    }

    @Override
    public void run() {
        while (isAc){
            System.out.println("run main");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
