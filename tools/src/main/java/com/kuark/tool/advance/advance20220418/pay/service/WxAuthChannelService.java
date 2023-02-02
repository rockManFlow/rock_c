package com.kuark.tool.advance.advance20220418.pay.service;

import com.kuark.tool.advance.advance20220418.pay.AbstractChannelService;
import com.kuark.tool.advance.advance20220418.pay.enums.ChannelServiceEnum;
import com.kuark.tool.advance.advance20220418.pay.model.sub.WxAuthReqModel;
import com.kuark.tool.advance.advance20220418.pay.model.sub.WxAuthRespModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * @author rock
 * @detail
 * @date 2022/4/18 15:21
 */
@Service
@Slf4j
public class WxAuthChannelService extends AbstractChannelService<WxAuthReqModel, WxAuthRespModel> {
    public WxAuthChannelService(){
        super(ChannelServiceEnum.WX_PAY_AUTH);
    }

    @Override
    public WxAuthRespModel invoke(WxAuthReqModel request) {
        //Ö´ÐÐ¼øÈ¨Âß¼­
        log.info("run invoke:{}",ChannelServiceEnum.WX_PAY_AUTH);
        return null;
    }
}
