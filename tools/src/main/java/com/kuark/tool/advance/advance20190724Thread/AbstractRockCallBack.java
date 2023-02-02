package com.kuark.tool.advance.advance20190724Thread;

/**
 * @author rock
 * @detail
 * @date 2021/7/13 11:19
 */
public abstract class AbstractRockCallBack<T> {
    private T t;
    public T get(){
        return t;
    }

    public void set(T t){
        this.t=t;
    }

}
