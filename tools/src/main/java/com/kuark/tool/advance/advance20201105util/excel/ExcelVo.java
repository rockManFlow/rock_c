package com.kuark.tool.advance.advance20201105util.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2021/10/13 14:57
 */
@Data
public class ExcelVo {
    @ExcelProperty("流水号")
    private String orderNo;

    @ExcelProperty("兑换ID")
    private String referId;

    @ExcelProperty("兑换名称")
    private String referName;

    @ExcelProperty("兑换物品类型")
    private String referType;

    @ExcelProperty("userId")
    private String userId;

    @ExcelProperty("OPay account")
    private String userPhone;
}
