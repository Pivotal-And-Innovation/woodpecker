package com.woodpecker.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 从property配置文件读取配置类
 *
 * @author Glenn
 * @since 2017-03-28
 */
public class CommonPropertyFactory extends PropertyPlaceholderConfigurer {

    private static Map<String, String> properties = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            properties.put(keyStr, value);
        }
    }

    /**
     * 由key获取属性，没有则返回给定的defaultValue
     *
     * @param key 属性key
     * @param defaultValue 默认值
     * @return 结果属性
     */
    public static String getProperty(String key, String defaultValue) {
        String value = properties.get(key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * 由key获取属性，且属性为Integer
     *
     * @param key 属性key
     * @return 属性Integer
     */
    public static Integer getPropertyInteger(String key) {
        String value = properties.get(key);
        return Integer.valueOf(value);
    }

}
