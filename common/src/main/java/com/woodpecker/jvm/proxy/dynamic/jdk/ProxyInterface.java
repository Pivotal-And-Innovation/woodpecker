package com.woodpecker.jvm.proxy.dynamic.jdk;

/**
 * 被代理类与代理类共同接口
 *
 * @author Relax
 * @since 2017年03月30日
 */
public interface ProxyInterface {
    void grandFather();

    void grandMother();

    void father();
}
