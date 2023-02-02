package com.kuark.tool.base.javaAPI.base.generic;

import java.io.Serializable;

/**
 * Created by caoqingyuan on 2018/1/17.
 */
public class GenMethod {
    //方法中药加上这个解释：表示这是一个泛型变量
    //会默认为原始类Object---只能使用Object的方法
    public <P> P get(P f){
        return f;
    }

    //使用类型限定---限定返回类型，可以多个限定类型
    public <U extends MyGen&Serializable> U gets(){
        return null;
    }
}
