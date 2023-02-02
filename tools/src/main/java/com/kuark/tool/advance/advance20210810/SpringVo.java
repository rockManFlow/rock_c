package com.kuark.tool.advance.advance20210810;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author rock
 * @detail BeanPostProcessor在spring bean实例化前后添加扩展功能
 * @date 2021/8/12 11:08
 */
public class SpringVo implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
