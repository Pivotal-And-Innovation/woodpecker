package com.woodpecker.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据库配置 体验：就自己动手配置这么多而言，感觉javaConfig给人的感觉更清新整洁，与spring整体融合也更好。XML配置更加具有层次感，解耦，标签识别能力很强大。就是看自己需求。
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
//@Configuration
//@MapperScan(basePackages = {"com.woodpecker.mapper"})
class DataBaseConfiguration {
    /**
     * 数据源属性
     */
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.minPoolSize}")
    private int minPoolSize;
    @Value("${jdbc.maxPoolSize}")
    private int maxPoolSize;
    @Value("${jdbc.maxIdleTime}")
    private int maxIdleTime;
    @Value("${jdbc.acquireIncrement}")
    private int acquireIncrement;
    @Value("${jdbc.initialPoolSize}")
    private int initialPoolSize;
    @Value("${jdbc.acquireRetryAttempts}")
    private int acquireRetryAttempts;
    @Value("${jdbc.idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;
    @Value("${jdbc.maxStatements}")
    private int maxStatements;

    @Bean(name = "dataSource", destroyMethod = "close")
    public ComboPooledDataSource DataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setAcquireRetryAttempts(acquireRetryAttempts);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        dataSource.setMaxStatements(maxStatements);
        dataSource.setBreakAfterAcquireFailure(false);
        dataSource.setTestConnectionOnCheckout(false);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean factoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        Resource[] resource = {new ClassPathResource("classpath:com/woodpecker/mapper/*.xml")};
        factoryBean.setDataSource(DataSource());
        factoryBean.setMapperLocations(resource);
        factoryBean.setConfigLocation(new PathResource("classpath:xml/datasource-config.xml"));
        return factoryBean;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager TransactionManager() throws Exception {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(DataSource());
        return manager;
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperConfig() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.woodpecker.mapper");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }

    /*
     * 以下为多数据源时候的配置
     */
    /*
    @Bean(name = "dataSourceAspect")
    public DataSourceAspect dataSourceAspect() {
        return new DataSourceAspect();
    }
    @Bean(name = "dataSourceRouter")
    public DataSourceRouter dataSourceRouter() throws Exception {
        DataSourceRouter router = new DataSourceRouter();
        Map<Object, Object> map = new HashMap<>();
        map.put("default", DataSource());
        map.put("ano", DataSource());
        router.setTargetDataSources(map);
        router.setDefaultTargetDataSource(DataSource());
        return router;
    }
    */

}
