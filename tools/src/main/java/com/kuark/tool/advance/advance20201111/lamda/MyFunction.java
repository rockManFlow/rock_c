package com.kuark.tool.advance.advance20201111.lamda;

import java.io.Serializable;

/**
 * @author rock
 * @detail 函数式接口需要继承Serializable ，java会帮我们序列化成SerializedLambda 对象，包含了函数式接口和实现方法的信息
 * @date 2021/1/25 15:12
 */
@FunctionalInterface
public interface MyFunction<T> extends Serializable {
    Object get(T t);
}
