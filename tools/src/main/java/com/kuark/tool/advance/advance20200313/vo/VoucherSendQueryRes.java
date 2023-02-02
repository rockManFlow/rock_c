package com.kuark.tool.advance.advance20200313.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author shiyuan
 * @date 2020/4/21
 */
@Data
public class VoucherSendQueryRes {
    //优惠卷Id
    @JsonProperty("voucher_id")
    private Long voucherId;
}
