package com.kuark.tool.advance.advance20201111.generics;

/**
 * @author rock
 * @detail
 * @date 2021/3/17 14:32
 */
public interface Listener<T> {
    void onMessage(T t);
}
