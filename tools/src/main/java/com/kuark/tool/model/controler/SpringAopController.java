package com.kuark.tool.model.controler;

import com.kuark.tool.model.springs.aop.MerterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by caoqingyuan on 2017/9/26.
 */
@Controller
public class SpringAopController {
    @Autowired
    private MerterService merterService;
    @RequestMapping("springAop/aop.do")
    public String aop(){
        System.out.println("SpringAopController...");
//        merterService.sed();
        System.out.println("=================");
        merterService.around("AAAAA");
        System.out.println("SpringAopController end");
        return "ok";
    }
}
