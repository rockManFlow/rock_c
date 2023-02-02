package com.kuark.tool.advance.advance20210810;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author rock
 * Spring 初始化 BeanFactory 时对外暴露的扩展点
 * @detail Spring IoC 容器允许 BeanFactoryPostProcessor 在容器实例化任何 bean 之前读取 bean 的定义，并可以修改它。
 * @date 2021/8/12 11:14
 */
@Component
public class BeanFactoryVo implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //在容器实例化任何 bean 之前读取 bean 的定义，并可以修改它
        //比如：扫描某个包路径，将该包路径下使用了某个注解的类全部注册到 Spring 中---我们可以自定义类似实体类注解@Component
//        beanFactory.getBeansWithAnnotation(Class)
    }
}
