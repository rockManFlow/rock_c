package com.kuark.tool.advance.advance20191120.listener;

import org.springframework.stereotype.Component;

/**
 * @author rock
 * @detail
 * @date 2019/11/25 20:30
 */
@Component
public class ConsumeRockListener implements RockListener {
    @Override
    public void onEvent(RockEvent event) {
        System.out.println("Event:"+event);
    }
}
