package com.woodpecker.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.woodpecker.model.DataBaseModel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;

/**
 * 数据库配置
 *
 * @author Glenn
 * @since 2017-03-29
 */
@Configuration
class DataBaseConfig {

    @Resource
    private DataBaseModel dataBaseModel;

    @Bean(value = "dataSource")
    public ComboPooledDataSource DataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(dataBaseModel.getDriver());
        dataSource.setJdbcUrl(dataBaseModel.getUrl());
        dataSource.setUser(dataBaseModel.getUser());
        dataSource.setPassword(dataBaseModel.getPassword());
        dataSource.setMinPoolSize(dataBaseModel.getMinPoolSize());
        dataSource.setMaxPoolSize(dataBaseModel.getMaxPoolSize());
        dataSource.setMaxIdleTime(dataBaseModel.getMaxIdleTime());
        dataSource.setAcquireIncrement(dataBaseModel.getAcquireIncrement());
        dataSource.setInitialPoolSize(dataBaseModel.getInitialPoolSize());
        dataSource.setAcquireRetryAttempts(dataBaseModel.getAcquireRetryAttempts());
        dataSource.setIdleConnectionTestPeriod(dataBaseModel.getIdleConnectionTestPeriod());
        dataSource.setMaxStatements(dataBaseModel.getMaxStatements());
        dataSource.setBreakAfterAcquireFailure(false);
        dataSource.setTestConnectionOnCheckout(false);
        return dataSource;
    }

    @Bean(value = "sqlSessionFactory")
    public SqlSessionFactoryBean factoryBean() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(DataSource());
//        factoryBean.setMapperLocations("classpath:com/woodpecker/mapper/*.xml");
//        factoryBean.setConfigLocation("classpath:dataSource-config.xml");
        return factoryBean;
    }

    @Bean(value = "transactionManager")
    public DataSourceTransactionManager TransactionManager() throws Exception {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(DataSource());
        return manager;
    }

    @Bean(value = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperConfig() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.woodpecker.mapper");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }

//    特别说明：被注释掉的为多数据源时候多配置(再配置另一个数据源多的dataSource)
//    多数据源配置
//            <bean id="dataSourceRouter" class="com.riskmanage.common.mybatis.DataSourceRouter">
//    <property name="targetDataSources">
//    <map key-type="java.lang.String" value-type="javax.sql.DataSource">
//    <entry key="default" value-ref="dataSource" />
//    <entry key="book" value-ref="bookDataSource" />
//    </map>
//    </property>
//    <property name="defaultTargetDataSource" ref="dataSource"/>
//    </bean>
//
//    <bean id="dataSourceAspect" class="com.riskmanage.common.mybatis.DataSourceAspect">
//    </bean>

}
