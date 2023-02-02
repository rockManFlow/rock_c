package com.kuark.tool.model.springs.datasource;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/13 14:54
 */
public class DataSourceAspect implements MethodBeforeAdvice,AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        if (method.isAnnotationPresent(DataSource.class))
        {
            DataSource datasource = method.getAnnotation(DataSource.class);
            DataSourceContextHolder.setDataSourceType(datasource.name());
        }
        else
        {
            DataSourceContextHolder.setDataSourceType("设置默认注解的数据源key");
        }
    }
}
