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
public class WxOrderChannelService extends AbstractChannelService<WxAuthReqModel, WxAuthRespModel> {

    public WxOrderChannelService(){
        super(ChannelServiceEnum.WX_PAY_ORDER);
    }

    @Override
    public WxAuthRespModel invoke(WxAuthReqModel request) {
        //执行对应通道逻辑
        log.info("run invoke:{}",ChannelServiceEnum.WX_PAY_ORDER);
        return null;
    }
}
