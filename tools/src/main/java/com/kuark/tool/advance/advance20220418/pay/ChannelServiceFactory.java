package com.kuark.tool.advance.advance20220418.pay;

import com.kuark.tool.advance.advance20220418.pay.enums.ChannelCodeEnum;
import com.kuark.tool.advance.advance20220418.pay.enums.ChannelServiceEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author rock
 * @detail
 * @date 2022/4/18 15:06
 */
public class ChannelServiceFactory {
    private static final Map<ChannelServiceEnum,IChannelService> channelServiceMap=new ConcurrentHashMap<>(16);

    private static final Map<String, ThreadPoolExecutor> channelPoolMap=new ConcurrentHashMap<>(16);

    public static IChannelService getChannelService(ChannelServiceEnum channelServiceEnum){
        IChannelService channelService = channelServiceMap.get(channelServiceEnum);
        if(channelService!=null){
            return channelService;
        }

        throw new RuntimeException("Not Support ChannelType "+ channelServiceEnum);
    }

    public static void register(ChannelServiceEnum channelServiceEnum, IChannelService channelService){
        channelServiceMap.put(channelServiceEnum,channelService);
    }

    public static ThreadPoolExecutor getThreadPool(ChannelCodeEnum channelCodeEnum){
        ThreadPoolExecutor poolExecutor = channelPoolMap.get(channelCodeEnum.getChannelCode());
        if(poolExecutor!=null){
            return poolExecutor;
        }

        throw new RuntimeException("Not Find ThreadPoolExecutor channelCode "+ channelCodeEnum);
    }

    public static void registerThreadPool(ChannelCodeEnum channelCodeEnum, ThreadPoolExecutor poolExecutor){
        if(channelPoolMap.containsKey(channelCodeEnum.getChannelCode())){
            return;
        }
        channelPoolMap.put(channelCodeEnum.getChannelCode(),poolExecutor);
    }
}
