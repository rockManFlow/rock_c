package com.kuark.tool.advance.advance20210810;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author rock
 * @detail 确定是IOC容器中所有bean初始化完成之后，才执行afterPropertiesSet方法吗？还是该对象初始化完成之后，就执行？
 * @date 2021/8/12 20:32
 */
public class BeanOne implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Bean(initMethod = "")
//    public void bean(){
//
//    }

    @PostConstruct
    public void init(){
        System.out.println("");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁使用");
    }
}
