package com.woodpecker.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据库集成配置属性模型类
 *
 * @author Glenn
 * @since 2017-03-29
 */
@ConfigurationProperties(prefix = "jdbc")
@Getter
@Setter
@Component
public class DataBaseModel {
    private String driver;
    private String url;
    private String user;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxIdleTime;
    private int acquireIncrement;
    private int initialPoolSize;
    private int acquireRetryAttempts;
    private int idleConnectionTestPeriod;
    private int maxStatements;
}
