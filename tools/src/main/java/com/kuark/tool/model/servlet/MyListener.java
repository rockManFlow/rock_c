package com.kuark.tool.model.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by caoqingyuan on 2017/3/22.
 */
public class MyListener implements ServletContextListener{
    private static final Logger logger= LoggerFactory.getLogger(MyListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("get ServletContext=======================Start");
        ServletContext context=sce.getServletContext();
        context.log("get ServletContext========================finished");
        String configLocationParam = context.getInitParameter("myConfigLocation");
//        context.log("自己配置文件的位置："+configLocationParam);
        logger.info("《《自己配置文件的位置》》："+configLocationParam);
        context.log("================Read Config File Context===============");
        logger.info("get ServletContext=======================End");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("============Destroy===========");
    }
}
