package com.kuark.tool.advance.advance20201105util.excel.advance1;

import java.util.List;

/**
 * @author rock
 * @detail
 * @date 2020/11/4 17:29
 */
public interface EasyExcelBizAdvanceService<T,S> {
    /**
     * 数据处理
     * @param listData
     */
    List<S> bizOperate(List<T> listData);
}
