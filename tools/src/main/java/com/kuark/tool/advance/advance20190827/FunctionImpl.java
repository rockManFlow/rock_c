package com.kuark.tool.advance.advance20190827;

import java.util.function.Consumer;

/**
 * @author rock
 * @detail 函数式编程
 * @date 2019/8/27 20:00
 */
public class FunctionImpl {
    public static void runFunction(Consumer<String> consumer,String ob){
        consumer.accept(ob);
    }

    public static void main(String[] args){
        ComputeImpl compute=new ComputeImpl();
        FunctionImpl.runFunction(compute::writeInfo,"sss");
    }
}
