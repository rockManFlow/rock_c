package com.kuark.tool.advance.advance20220418.pay;

import com.kuark.tool.advance.advance20220418.pay.enums.ChannelServiceEnum;
import com.kuark.tool.advance.advance20220418.pay.model.ReqModel;
import com.kuark.tool.advance.advance20220418.pay.model.RespModel;

/**
 * @author rock
 * @detail
 * @date 2022/4/18 15:03
 */
public abstract class AbstractChannelService<T extends ReqModel,R extends RespModel> implements IChannelService<T,R>{
    public AbstractChannelService(ChannelServiceEnum channelServiceEnum){
        ChannelServiceFactory.register(channelServiceEnum,this);
    }
}
