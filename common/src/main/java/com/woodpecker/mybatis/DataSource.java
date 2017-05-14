package com.woodpecker.mybatis;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * 数据源默认名称
     */
    String value() default "anoData";
}
