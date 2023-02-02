package com.kuark.tool.advance.advance20201111;

/**
 * @author rock
 * @detail
 * @date 2021/8/3 17:29
 */
public class BoA {
    public BoA(){
        System.out.println("init BoA");
    }
    {
        System.out.println("base BoA context");
    }
    static {
        //1
        System.out.println("static BoA");
    }
}
