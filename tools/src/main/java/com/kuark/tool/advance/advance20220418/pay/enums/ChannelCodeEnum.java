package com.kuark.tool.advance.advance20220418.pay.enums;

import lombok.Getter;

/**
 * @author rock
 * @detail
 * @date 2022/4/18 17:45
 */
@Getter
public enum ChannelCodeEnum {
    WX_PAY("WX_PAY",""),
    WX_H5("WX_H5",""),
    ZFB_PAY("ZFB_PAY",""),
    ;
    private String channelCode;
    private String desc;

    ChannelCodeEnum(String channelCode,String desc){
        this.channelCode=channelCode;
        this.desc=desc;
    }
}
