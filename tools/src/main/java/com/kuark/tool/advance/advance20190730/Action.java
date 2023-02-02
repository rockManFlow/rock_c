package com.kuark.tool.advance.advance20190730;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/30 15:04
 */
public interface Action {
    /**
     * 不同接口请求参数组装请求体
     * @return
     */
    Object assembleParam();
    /**
     * 发送请求功能处理
     */
    Object sendReq(Object req);

    /**
     * 组装成前端系统需要的响应体
     * @return
     */
    Object assembleResp(Object resp);
}
