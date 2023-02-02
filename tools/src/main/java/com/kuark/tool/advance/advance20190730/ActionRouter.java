package com.kuark.tool.advance.advance20190730;

import org.springframework.stereotype.Service;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/30 11:55
 */
@Service("actionRouter")
public class ActionRouter {
    /**
     * channel+本系统接口名+渠道接口名(下游请求地址)
     */

    /**
     * channel+localApi+AbstractAction
     * @param channel
     * @param localApi
     * @return
     */
    public static AbstractAction filterAction(String channel, String localApi) throws IllegalAccessException, InstantiationException {
        ApiEnum apiEnum=ApiEnum.getApiAction(channel,localApi);

        return (AbstractAction) apiEnum.getActionClass().newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        AbstractAction action = filterAction("", "");
        action.execute();
    }
}
