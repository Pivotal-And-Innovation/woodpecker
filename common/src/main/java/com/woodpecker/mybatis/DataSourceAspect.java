package com.woodpecker.mybatis;


import com.woodpecker.util.Logger;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 切换数据源
 * 请注意：这里order一定要小于tx:annotation-driven的order，即先执行DataSourceAspect切面，再执行事务切面，才能获取到最终的数据源
 *
 * @author Relax
 * @since 2017年03月30日
 */
@Order(1)
public class DataSourceAspect {
    /**
     * 使用数据源前设置
     *
     * @param point 切入点
     */
    public void before(JoinPoint point) {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        DataSource dataSource = this.getDataSource(target, method);
        if(dataSource != null && StringUtils.isNotBlank(dataSource.value()) ){
            DataSourceHandler.setDataSource(dataSource.value());
        }
    }

    /**
     * 使用完后释放数据源
     *
     * @param point 切入点
     */
    public void after(JoinPoint point) {
        DataSourceHandler.setDataSource(null);
    }

    /**
     * 获取数据源 {@code DataSource}
     *
     * @param target 目标Class
     * @param method 目标Method
     * @return 数据源注解
     */
    private DataSource getDataSource(Class<?> target, Method method) {
        try {
            if (target.isAnnotationPresent(DataSource.class)) {
                return target.getAnnotation(DataSource.class);
            }
        } catch (Exception e) {
            Logger.error("通过注解切换数据源时出错，@class = " + target.getName() + ", @method = " + method.getName(), e);
        }

        return null;
    }

}
