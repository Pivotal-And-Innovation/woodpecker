package com.woodpecker.mybatis;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author Glenn
 * @since 2017-03-27
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
