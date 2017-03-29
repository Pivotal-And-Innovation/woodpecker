package com.woodpecker.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Glenn
 * @since 2017-03-27
 */
class ProxyHandler implements InvocationHandler {

    private ProxyInterface proxyInterface;

    ProxyHandler(ProxyInterface proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("@before...");

        Object var = method.invoke(proxyInterface,args);

        System.out.println("@after...");
        return var;
    }

}
