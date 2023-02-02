package com.kuark.tool.advance.advance20191120;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author rock
 * @detail
 * @date 2019/11/22 14:19
 */
@Builder
@Getter
public class QueryReq implements Serializable {
    /**
     * 原充值订单号
     */
    private String orderID;
    /**
     * 交易订单号
     */
    private String transactionID;
}
