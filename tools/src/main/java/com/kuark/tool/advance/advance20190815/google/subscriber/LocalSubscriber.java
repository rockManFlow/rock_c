package com.kuark.tool.advance.advance20190815.google.subscriber;

/**
 * @author rock
 * @detail 订阅者
 * @date 2019/9/3 11:33
 */
public interface LocalSubscriber<T> {
    public void onHandle(T t);
}
