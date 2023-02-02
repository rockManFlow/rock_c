package com.kuark.tool.advance.advance20190730.dto;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/31 10:28
 */
public enum RespCodeType {

    /**
     * 0000-0002 业务处理结果
     */
    SUCCESS("0000", "接口响应成功"),
    FAIL("0001", "接口响应失败"),
    ILLEGAL_ARGUMENT("0003", "参数不符合规范,{0}"),
    SYSTEM_ERROR("9999","系统异常,{0}"),
    ;

    private String respCode;
    private String respMsg;

    RespCodeType(String respCode,String respMsg){
        this.respCode=respCode;
        this.respMsg=respMsg;
    }
}
