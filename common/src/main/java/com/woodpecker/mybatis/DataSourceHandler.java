package com.woodpecker.mybatis;

/**
 * 数据源配置
 *
 * @author Glenn
 * @since 2017-03-27
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
