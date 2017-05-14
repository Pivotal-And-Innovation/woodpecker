package com.woodpecker.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由
 *
 * @author Relax
 * @since 2017年03月30日
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
