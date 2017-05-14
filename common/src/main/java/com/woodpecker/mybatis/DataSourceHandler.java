package com.woodpecker.mybatis;

/**
 * 数据源配置
 *
 * @author Relax
 * @since 2017年03月30日
 */
final class DataSourceHandler {
    /**
     * threadLocal
     */
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /**
     * 配置数据源
     *
     * @param dataSource 数据源名称
     */
    static void setDataSource(String dataSource) {
        holder.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return 数据源名称
     */
    static String getDataSource() {
        return holder.get();
    }

}
