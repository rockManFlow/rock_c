package com.kuark.tool.advance.advance20201105util.excel.base;

import java.util.List;

/**
 * @author rock
 * @detail
 * @date 2020/11/4 17:29
 */
public interface EasyExcelBizService<T> {
    void bizOperate(List<T> listData);
}
