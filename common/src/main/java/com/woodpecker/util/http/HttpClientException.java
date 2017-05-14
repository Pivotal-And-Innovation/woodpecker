package com.woodpecker.util.http;

/**
 * http请求异常
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public class HttpClientException extends RuntimeException {
    private static final long serialVersionUID = -819216796235914046L;

    public HttpClientException() {

    }

    HttpClientException(Exception cause) {
        super(cause);
    }

    public HttpClientException(String msg) {
        super(msg);
    }
}
