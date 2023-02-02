package com.kuark.tool.advance.advance20190815.google.subscriber;

import com.google.common.eventbus.Subscribe;
import com.kuark.tool.advance.advance20190815.google.BaseEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail 订阅者
 * @date 2019/9/3 11:42
 */
@Slf4j
public class TestSubscriber implements LocalSubscriber<BaseEvent> {

    @Subscribe
    @Override
    public void onHandle(BaseEvent event) {
        log.info("event="+event.getContext());
    }
}
