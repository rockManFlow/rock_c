package com.kuark.tool.base.javaAPI.base.generic;

import java.util.List;

/**
 * 也可以接口中指定泛型类型变量
 * Created by caoqingyuan on 2018/1/17.
 */
public interface InterfGen<P> {
    List<P> getList();
}
