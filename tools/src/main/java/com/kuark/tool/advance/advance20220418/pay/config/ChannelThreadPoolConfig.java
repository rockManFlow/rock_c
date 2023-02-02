package com.kuark.tool.advance.advance20220418.pay.config;

import com.kuark.tool.advance.advance20220418.pay.ChannelServiceFactory;
import com.kuark.tool.advance.advance20220418.pay.enums.ChannelCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * @author rock
 * @detail 方便配置线程池参数
 * @date 2022/4/18 17:03
 */
@Configuration
@Slf4j
public class ChannelThreadPoolConfig {

    @PostConstruct
    public void init(){
        ChannelServiceFactory.registerThreadPool(ChannelCodeEnum.WX_PAY,wxChannelPool());
        ChannelServiceFactory.registerThreadPool(ChannelCodeEnum.WX_H5,wxChannelPool());
        ChannelServiceFactory.registerThreadPool(ChannelCodeEnum.ZFB_PAY,zfbChannelPool());
    }

    public ThreadPoolExecutor wxChannelPool(){
        return new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t=new Thread(r);
                t.setName("wx-channel-pool-");
                t.setDaemon(false);
                return t;
            }
        },new ThreadPoolExecutor.DiscardPolicy());
    }

    public ThreadPoolExecutor zfbChannelPool(){
        return new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t=new Thread(r);
                t.setName("zfb-channel-pool-");
                t.setDaemon(false);
                return t;
            }
        },new ThreadPoolExecutor.DiscardPolicy());
    }
}
