package com.kuark.tool.model.test;

import org.apache.log4j.Logger;
/**
 * Created by caoqingyuan on 2016/6/29.
 */
public class Ant {
    private static Logger logger= Logger.getLogger("Ant");
    public static void main(String[] args){
        System.out.println("这是一个测试ant的main");
        for(int i=0;i<10;i++){
            logger.info("i="+i);
        }
        System.out.println("end");
    }
}
