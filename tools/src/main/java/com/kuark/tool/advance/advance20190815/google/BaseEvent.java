package com.kuark.tool.advance.advance20190815.google;

/**
 * @author rock
 * @detail
 * @date 2019/9/3 10:42
 */
public interface BaseEvent<T> {
    void setContext(T t);

    T getContext();
}
