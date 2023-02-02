package com.kuark.tool.advance.advance20190724Thread.nio.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2022/3/28 11:47
 */
@Slf4j
public class Service2InterfaceImpl implements Service2Interface {
    @Override
    public String writeEvent(String param, Long id) {
        System.out.println("Service2InterfaceImpl#writeEvent param:{},id:{}"+param+id);
        try {
            //类似于网络等业务逻辑处理
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            log.error("",e);
        }
        System.out.println("end|"+param);
        return param+"OK";
    }
}
