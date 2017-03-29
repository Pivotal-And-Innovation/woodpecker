package com.woodpecker.jvm.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Glenn
 * @since 2017-03-27
 */
public class ProxyMain {

    public static void main(String[] args) {

        ProxyInterfaceImpl impl = new ProxyInterfaceImpl();
        ProxyHandler handler = new ProxyHandler(impl);

        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),impl.getClass().getInterfaces(),handler);
        proxy.doSomething();
    }

}
