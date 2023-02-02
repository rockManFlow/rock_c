package com.kuark.tool.advance.advance20201111.design.observer;

public class Main {
    public static void main(String[] args) {
        FInishSubjectObserver fInishSubjectObserver=new FInishSubjectObserver();
        //.....逻辑执行
        ActivityObserver activityObserver=new ActivityObserver();

        fInishSubjectObserver.register(activityObserver);
        //.....逻辑执行
        fInishSubjectObserver.notifyObserver(1L);

    }
}
