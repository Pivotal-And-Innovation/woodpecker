package com.woodpecker.jvm.proxy.dynamic.jdk;

/**
 * 被代理类与代理类共同接口
 *
 * @author Glenn
 * @since 2017-04-12
 */
public interface ProxyInterface {
    void grandFather();

    void grandMother();

    void father();
}
