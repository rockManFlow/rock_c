package com.kuark.tool.base.test;

import org.junit.Test;

/**
 * Created by cc on 2016/4/14.
 * 枚举测试
 */
public class Enum {
    @Test
    public void test3(){
        ColorEnum c=ColorEnum.green;
        System.out.println("c="+c);
    }
    public enum ColorEnum {
        red, green, yellow, blue;
    }
    public enum ColorEnum1{
        red, black;
    }

}
