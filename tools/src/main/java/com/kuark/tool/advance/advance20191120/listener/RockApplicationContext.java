package com.kuark.tool.advance.advance20191120.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author rock
 * @detail
 * @date 2019/11/25 20:35
 */
@Component
public class RockApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     * 获取指定类的所有实现类
     * @param cal
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeanByClass(Class<T> cal){
        return applicationContext.getBeansOfType(cal);
    }
}
