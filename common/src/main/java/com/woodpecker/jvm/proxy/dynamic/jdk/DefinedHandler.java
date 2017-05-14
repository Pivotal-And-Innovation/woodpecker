package com.woodpecker.jvm.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义handler：动态调用处理器，相当于连接被代理类和代理类的中介类<br>
 * 调用代理对象的每个函数实际最终都是调用了InvocationHandler的invoke函数。<br>
 * 所以invoke函数中我们也可以通过对method做一些判断，从而对某些函数特殊处理。
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public class DefinedHandler implements InvocationHandler {

    /**
     * 持有被代理类引用
     */
    private BeenProxyMain beenProxyMain = new BeenProxyMain();

    /**
     * 中介类调用代理类
     *
     * @param proxy 代理类对象
     * @param method 代理类被调用方法
     * @param args 调用参数
     * @return object
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Time Log Start.");
        long start = System.currentTimeMillis();
        Object object = method.invoke(beenProxyMain, args);
        long end = System.currentTimeMillis();
        System.out.println("Time Log End.");
        System.out.println(method.getName() + " cost time :" + (end - start) + ".");
        return object;
    }
}
