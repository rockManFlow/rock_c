package com.kuark.tool.model.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caoqingyuan on 2018/1/12.
 */
public class TestLog {
    private static final Logger logger= LoggerFactory.getLogger(TestLog.class);
    public static void main(String[] args){
        for(int i=0;i<20;i++){
            logger.info("hhhhh"+i);
        }
    }
}
