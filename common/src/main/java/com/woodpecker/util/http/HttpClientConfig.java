package com.woodpecker.util.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.config.RequestConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求配置
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@Getter
@Setter
public class HttpClientConfig {
    /**
     * 字符集
     */
    private String charset = "UTF-8";
    /**
     * 请求头
     */
    private Map<String, String> header = new HashMap<>();
    /**
     * 连接超时时间
     */
    private int connectTimeout = 5000;
    /**
     * the {@code Connect Manager} acquire {@code Connection} timeout, unit:millions. It's a new added property, because
     * the {@code Connection Pool} is shared in the current version
     */
    private int connectionRequestTimeout = 5000;
    /**
     * 获取响应超时时间, unit:millions
     */
    private int socketTimeout = 60000;

    /**
     * {@code RequestConfig} builder
     *
     * @return builder
     */
    RequestConfig buildRequestConfig() {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(connectTimeout);
        builder.setConnectionRequestTimeout(connectionRequestTimeout);
        builder.setSocketTimeout(socketTimeout);
        return builder.build();
    }

    public void setHeader(String key, String value) {
        this.header.put(key, value);
    }

}
