package com.kuark.tool.advance.advance20201111;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @author rock
 * @detail 验证method.invoke导致的JVM崩溃问题
 * @date 2021/1/20 17:26
 */
public class MethodMain {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Store s=new Store();
        Apple apple=new Apple();
        apple.setPrice(new BigDecimal(11));
        s.setFruit(apple);
        s.setName("testa");
        MethodMain mm=new MethodMain();
        Method method = mm.getClass().getMethod("show", Store.class);
        method.invoke(mm,s);
//        ObjectInputStream.class.getDeclaredMethod("readObject",)
        try{
            mm.write();
        }catch (Throwable t){
            System.out.println("main"+t.getMessage());
        }
        System.out.println("main end");
    }

    public void write(){
        System.out.println("write error");
        throw new OutOfMemoryError("test error");
    }

    public void show(Store s){
        System.out.println(s);
    }
}
