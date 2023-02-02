package com.kuark.tool.advance.advance20201111.design.adapter;

/**
 * @author rock
 * @detail 适配器简单理解：这个通过继承实现属于类适配器、还有对象适配器（通过对象注入，扩展功能）
 * @date 2021/8/9 10:47
 */
public class AdapterA extends ApiService implements Target {
    @Override
    public void write(Integer age) {
        System.out.println("write age"+age);
    }
}
