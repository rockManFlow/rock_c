package com.kuark.tool.advance.advance20191114.arithmetic.googleInject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * @author rock
 * @detail
 * @date 2019/11/20 17:12
 */
public class InjectLearnMain {
    /**
     * TODO 把接口与实现类通过Google的这种方式进行绑定，
     * 之后通过这个类就可以直接获取到实例对象
     */
    private static com.google.inject.Injector m_injector;
    public static void main(String[] args){
        m_injector = Guice.createInjector(new Module[]{new InjectLearnMain.LearnModule()});
        Manager manager = m_injector.getInstance(Manager.class);
        manager.show();
    }

    private static class LearnModule extends AbstractModule{

        /**
         * 这个地方进行绑定接口+接口实现类
         */
        @Override
        protected void configure() {
            //绑定接口到实现类，模式是单例
            this.bind(Manager.class).to(ManagerImpl.class).in(Singleton.class);
        }
    }
}
