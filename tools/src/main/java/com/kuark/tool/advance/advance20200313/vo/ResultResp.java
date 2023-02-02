package com.kuark.tool.advance.advance20200313.vo;

import lombok.Data;

/**
 * @author wangzhuo
 * @ClassName: ResultMessage
 * @Description: 服务器返回对象描述类
 * @date 2016年7月31日 下午8:24:23
 */
@Data
public class ResultResp<T> {
    public static final String SUCCESS_CODE = "00000";

    /**
     * 返回代码
     */
    private String code;

    /**
     * 操作提示信息
     */
    private String message;

    /**
     * 操作数据
     */
    private T data;

    public ResultResp() {
    }

    public ResultResp(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.code);
    }
}
