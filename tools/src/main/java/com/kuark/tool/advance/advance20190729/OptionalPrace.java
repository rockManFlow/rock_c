package com.kuark.tool.advance.advance20190729;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/29 18:11
 */
public class OptionalPrace {
    public static void main1(String[] args) throws Exception {
        Optional.of(new String());//参数不可以为空
        //参数可以为空///参数不为空
        Optional.ofNullable(null);
        Optional.ofNullable(1);
        Optional.empty();//所有null包装成Optional对象
        //判断值是否存在（是否是null)
        Optional.ofNullable("1111").isPresent();
        //为null不执行，不为null，执行
        Optional.ofNullable("1111").ifPresent(t->t.length());
        //为null，返回默认值。不为null，则返回
        Optional.empty().orElse("1111");
        //与orElse一样，只是是个函数
        Optional.ofNullable("1111").orElseGet(()->{return "11";});
        Optional.empty().orElseThrow(()->{return new Exception();});//不存在抛异常
        //判断指定值，并返回Optional对象
        Optional.ofNullable("1111").filter(t->true);
        //为null,返回Optional.不为null，执行函数
        Optional.ofNullable("222").map(t->{return true;});
    }

    public void localdate(){
//        LocalDateTime
    }

    public static void main(String[] args) throws Exception {
        func("",new PraceClass()::getString);
    }

    public static  <T, R> R func(T req,Function<T, R> fun){
        return fun.apply(req);
    }
}
