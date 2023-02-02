package com.kuark.tool.advance.advance20191005;

import java.util.Optional;

/**
 * @author rock
 * @detail
 * @date 2019/11/14 20:48
 */
public class OptionalPractice {
    public static void main(String[] args) throws Exception {
        //创建Optional对象
        //必须是非空值，为空抛空指针异常
        Optional<Integer> optional = Optional.of(20);
        //创建一个空的对象
        Optional<Object> empty = Optional.empty();
        //允许创建一个可空可非空
        Optional<Integer> optional1 = Optional.ofNullable(null);

        OptionalnterfaceImpl optionalnterface=new OptionalnterfaceImpl();
        //如果不为空，执行optional中的值到指定函数中
        optional.ifPresent(optionalnterface::write);
        //为空则不执行
        optional1.ifPresent(optionalnterface::write);
        //判断是否为空
        empty.isPresent();

        //不为空返回，为空抛异常
        optional1.get();
        //返回指定值
        Object orElse = empty.orElse("10");
        Integer anElse = optional.orElse(30);
        System.out.println("为空返回指定值"+orElse);
        System.out.println("不为空返回原值"+anElse);

        //为空，就获取指定函数中的值
        optional1.orElseGet(optionalnterface::pull);

        //为空，抛出指定异常
        optional1.orElseThrow(()->new Exception());
    }
}
