package com.kuark.tool.model.springs.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

/**
 * Created by caoqingyuan on 2017/7/4.
 */
@Service
public class MerterService {
    private String dos(){
        System.out.println("MerterService11 dos");
        return "dos11";
    }
    public void main(){
        System.out.println("MerterService11 main");
        String dos = dos();
        System.out.println("main result:"+dos);
    }
    public void sed(){
        System.out.println("MerterService11 sed start");
        System.out.println("MerterService11 sed end");
    }
    public String around(String name){
        System.out.println("around start");
        System.out.println("name:"+name);
        System.out.println("around end");
        return "OOOOOKKK";
    }
}
