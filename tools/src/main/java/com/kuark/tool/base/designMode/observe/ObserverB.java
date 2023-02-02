package com.kuark.tool.base.designMode.observe;

/**
 * Created by caoqingyuan on 2017/6/5.
 */
public class ObserverB implements Observer {
    @Override
    public void update(Observable o) {
        System.out.println("ObserverB update");
    }
}
