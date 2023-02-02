package com.kuark.tool.advance.advance20190730;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/31 19:35
 */
@RestController
public class CallBackController {

    @RequestMapping("/trade/{tradeType}/callback")
    public void callback(@RequestParam("tradeType") String tradeType, @RequestBody Map req){

    }
}
