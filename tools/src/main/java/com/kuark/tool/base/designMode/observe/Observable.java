package com.kuark.tool.base.designMode.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoqingyuan on 2017/6/5.
 */
public class Observable {
    private List<Observer> observers=new ArrayList<Observer>();
    public void add(Observer o){
        observers.add(o);
    }
    public void changed(){
        System.out.println("我是被观察者，我已发生改变");
        notifyObserver();
    }
    private void notifyObserver(){
        for(Observer o:observers){
            o.update(this);
        }
    }
}
