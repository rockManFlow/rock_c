package com.kuark.tool.advance.advance20191120.listener;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author rock
 * @detail
 * @date 2019/11/25 20:35
 */
@Component
public class RockListenerManager implements ListenerMananger{

    @Override
    public boolean sendEvent(RockEvent event) {
        Map<String, RockListener> beanListenerList = RockApplicationContext.getBeanByClass(RockListener.class);
        beanListenerList.keySet().stream().forEach(consumerBeanName->{
            beanListenerList.get(consumerBeanName).onEvent(event);
        });
        return true;
    }
}
