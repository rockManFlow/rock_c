package com.kuark.tool.model.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
@RestController
public class SpringmvController {
    private static final Logger logger= LoggerFactory.getLogger(SpringmvController.class);
    //测试高并发访问同一个单利对象的相同方法(没有成员变量)，会出现什么问题
    @RequestMapping("springmvc/checkCon.p")
    public String checkCon(String name,Integer data) throws InterruptedException {
        logger.info("checkCon");
        Thread.sleep(1000);
        return "OK";
    }
}
