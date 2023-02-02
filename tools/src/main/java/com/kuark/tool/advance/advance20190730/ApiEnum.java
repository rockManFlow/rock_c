package com.kuark.tool.advance.advance20190730;

import lombok.Getter;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/30 14:39
 */
@Getter
public enum ApiEnum {

    PRE_CREDIT("pre","credit",PreCreditAbstractAction.class),
    ;






    public static ApiEnum getApiAction(String channel,String localApi){
        for(ApiEnum api:ApiEnum.values()){
            if(api.getChannel().equals(channel)&&api.getLocalApi().equals(localApi)){
                return api;
            }
        }
        return null;
    }


    private String channel;
    private String localApi;
    private Class<Action> actionClass;

    ApiEnum(String channel,String localApi,Class actionClass){
        this.channel=channel;
        this.localApi=localApi;
        this.actionClass=actionClass;
    }
}
