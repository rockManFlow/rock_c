package com.kuark.tool.advance.advance20201111.generics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author rock
 * @detail
 * @date 2021/3/17 14:33
 */
@Component
@Slf4j
public class MyListener implements Listener<MyMessage> {
    @Override
    public void onMessage(MyMessage myMessage) {
        log.info("MyListener:{}",myMessage);
    }


}
