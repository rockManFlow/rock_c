package com.kuark.tool.base.designMode.observe;

/**
 * Created by caoqingyuan on 2017/6/5.
 */
public class ObserverA implements Observer {
    @Override
    public void update(Observable o) {
        System.out.println("A update observed");
    }
}
