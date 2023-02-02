package com.kuark.tool.advance.advance20201111.apollo.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.kuark.tool.model.util.ConfigUtil;

/**
 * @author rock
 * @detail 一组绑定接口与实现类
 * @date 2021/4/1 14:15
 */
public class MyModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(WriteInterface.class).to(WriteInterfaceImpl.class).in(Singleton.class);
        //也可以单独绑定实现类
        bind(ConfigUtil.class).in(Singleton.class);
        //....可以多个
    }
}
