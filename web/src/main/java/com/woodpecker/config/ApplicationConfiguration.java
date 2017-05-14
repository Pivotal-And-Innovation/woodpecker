package com.woodpecker.config;

import com.woodpecker.ApplicationMain;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

/**
 * 项目配置-扫描注册根目录下所有的bean，同时配置一些小规模零散的bean
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@Configuration
@ComponentScan(basePackageClasses = {ApplicationMain.class}, excludeFilters = @ComponentScan.Filter({Controller.class,
        Configuration.class}))
@ImportResource(value = "classpath:spring/mybatis-config.xml")
class ApplicationConfiguration {

}
