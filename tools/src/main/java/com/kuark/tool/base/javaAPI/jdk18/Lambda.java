package com.kuark.tool.base.javaAPI.jdk18;

import java.util.Arrays;
import java.util.List;

/**
 * Created by caoqingyuan on 2017/10/25.
 */
public class Lambda {
    public static void main(String[] args){
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);
        players.forEach((plsy)->{
            System.out.println(plsy);
        });

        players.stream().sorted();
    }

    public void ppp(){
        //使用lambda实现Runnable接口的实例
        Runnable runnable=()->{
            System.out.println("111");
            System.out.println("222");
        };//相当于接口中的run方法的实现
    }
}
