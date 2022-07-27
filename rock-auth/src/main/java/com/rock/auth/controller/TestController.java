package com.rock.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "one")
    public String test1(){
        log.info("TestController test1");
        return "test1";
    }
}
