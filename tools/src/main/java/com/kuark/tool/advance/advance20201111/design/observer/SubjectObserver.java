package com.kuark.tool.advance.advance20201111.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义成抽象类，为了实现不同的观察者目标对象
 */
public abstract class SubjectObserver {
    List<Observer> observers=new ArrayList<>();

    public void register(Observer observer){
        observers.add(observer);
    }

    public void remove(Observer observer){
        observers.remove(observer);
    }

    /**
     * 观察者通知
     * @param taskId
     */
    public void notifyObserver(Long taskId){
        for(Observer observer:observers){
            observer.notify1(taskId);
        }
    }
}
