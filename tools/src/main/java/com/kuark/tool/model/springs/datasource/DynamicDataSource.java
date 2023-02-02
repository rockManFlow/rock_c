package com.kuark.tool.model.springs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author caoqingyuan
 * @detail 动态数据源
 * @date 2019/3/13 14:42
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 该方法可以实现数据源的动态切换
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
