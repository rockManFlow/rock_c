package com.kuark.tool.advance.advance20220418.pay;

import com.kuark.tool.advance.advance20220418.pay.model.ReqModel;
import com.kuark.tool.advance.advance20220418.pay.model.RespModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author rock
 * @detail µ÷¶ÈÆ÷
 * @date 2022/4/18 15:17
 */
@Service
@Slf4j
public class ChannelServiceDispatch {
    public void dispatch(@Validated ReqModel reqModel){
        if(reqModel==null){
            return;
        }

        log.info("dispatch ChannelService:{}",reqModel.getChannelServiceEnum().name());
        IChannelService channelService = ChannelServiceFactory.getChannelService(reqModel.getChannelServiceEnum());

        ThreadPoolExecutor threadPool = ChannelServiceFactory.getThreadPool(reqModel.getChannelServiceEnum().getChannelCode());

        threadPool.execute(()->{
            try {
                log.info("dispatch ChannelService:{},req:{}",reqModel.getChannelServiceEnum().name(),reqModel);
                RespModel respModel = channelService.invoke(reqModel);
                log.info("dispatch ChannelService:{},resp:{}",reqModel.getChannelServiceEnum().name(),respModel);
            }catch (Exception e){
                log.error("dispatch error",e);
            }
        });


        //......
    }


}
