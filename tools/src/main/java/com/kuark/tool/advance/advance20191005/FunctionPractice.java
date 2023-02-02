package com.kuark.tool.advance.advance20191005;

import lombok.extern.log4j.Log4j;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author rock
 * @detail
 * @date 2019/11/12 16:47
 */
@Log4j
public class FunctionPractice {
    public static void main(String[] args){
        FacadeImpl1 facade=new FacadeImpl1();//实例对象
        log.info("main start");
        Function<String, String> stringStringFunction = facade::show;
        stringStringFunction.apply("ssss");
        facade(facade::show,"xiao");
        facade2((input1,input2)->facade.write((Integer) input1,(String)input2),11,"sss");
        log.info("main end");
    }
    /**
     * public interface Function<T,R> jdk1.8
     * 表示接受一个函数并产生结果的函数
     * @param fun
     */
    public static  <T,R> R facade(Function<T,R> fun,T input){
        System.out.println("Run Input="+input);
        return fun.apply(input);
    }

    /**
     * public interface BiFunction<T,U,R>
     * 表示接受两个参数并产生结果的函数。
     * @param fun
     * @param input1
     * @param input2
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <T,U,R> R facade2(BiFunction<T,U,R> fun,T input1,U input2){
        return fun.apply(input1,input2);
    }
}
