package com.woodpecker.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 自定义bean工厂
 *
 * @author Glenn
 * @since 2017-03-25
 */
public class CommonBeanFactory implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 通过bean名称获取bean
     *
     * @param beanName bean名字
     * @return bean
     */
    public static Object getBean(String beanName) {
        if (context == null || StringUtils.isBlank(beanName)) {
            return null;
        }
        return context.getBean(beanName);
    }

    /**
     * 通过Class获取bean
     *
     * @param className Class
     * @return bean
     */
    public static <T> T getBean(Class<T> className) {
        if (context == null || className == null) {
            return null;
        }
        return context.getBean(className);
    }

}
