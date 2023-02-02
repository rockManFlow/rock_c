package com.kuark.tool.model.oper;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by caoqingyuan on 2016/10/13.
 * 把这个类注册到spring中，实现监控类就可以获取到spring的实例对象
 * 在启动服务器时就会对他进行初始化
 */
@Component
public class AppContext implements ApplicationContextAware{
    private static final Logger logger=Logger.getLogger(AppContext.class);
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("inited applicationContext finished");
        this.applicationContext=applicationContext;
    }
    //得到spring上下文实体对象
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    //获取连接池对象
    public static DataSource createDataSource(){
        return (DataSource)applicationContext.getBean("dataSource");
    }
}
