package com.kuark.tool.advance.advance20220418.pay.enums;

import lombok.Getter;

/**
 * @author rock
 * @detail
 * @date 2022/4/18 14:54
 */
@Getter
public enum ChannelServiceEnum {
    WX_PAY_AUTH("WX_PAY_AUTH",ChannelCodeEnum.WX_PAY,""),
    WX_PAY_ORDER("WX_PAY_ORDER",ChannelCodeEnum.WX_PAY,""),
    WX_PAY_QUERY("WX_PAY_QUERY",ChannelCodeEnum.WX_PAY,""),
    WX_H5_ORDER("WX_H5_ORDER",ChannelCodeEnum.WX_H5,""),
    ZFB_PAY_ORDER("ZFB_PAY_ORDER",ChannelCodeEnum.ZFB_PAY,""),
    ZFB_PAY_QUERY("ZFB_PAY_QUERY",ChannelCodeEnum.ZFB_PAY,""),
    ;

    /**
     * 通道步骤
     */
    private String serviceCode;
    /**
     * 通道类型
     */
    private ChannelCodeEnum channelCode;
    /**
     * 描述
     */
    private String desc;
    ChannelServiceEnum(String serviceCode, ChannelCodeEnum channelCode, String desc){
        this.serviceCode=serviceCode;
        this.channelCode=channelCode;
        this.desc=desc;
    }
}
