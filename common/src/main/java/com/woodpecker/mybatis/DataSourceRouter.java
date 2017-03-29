package com.woodpecker.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由
 *
 * @author Glenn
 * @since 2017-03-27
 */
public class DataSourceRouter extends AbstractRoutingDataSource {
    /**
     * 获取数据源名称
     *
     * @return 数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHandler.getDataSource();
    }

}
