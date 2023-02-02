package com.kuark.tool.base.excel.vo;

import com.kuark.tool.base.excel.ExcelTitle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author caoqingyuan
 * @detail
 */
@Setter
@Getter
@Builder
public class AdminOrderQueryVo implements Serializable {
    /**
     * 流水号
     */
    @ExcelTitle("流水号")
    private String transNo;

    /**
     * 订单类型：1-领取，2-兑换
     */
    @ExcelTitle("type")
    private Integer orderType;

    /**
     * 任务id或兑换ID
     */
    @ExcelTitle("任务id或兑换ID")
    private String referId;

}
