package com.woodpecker.jvm.proxy.dynamic.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 自定义方法拦截器
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class DefinedInterceptor implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();
    private static DefinedInterceptor instance = new DefinedInterceptor();
    public static DefinedInterceptor getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public  <T> T getProxy(Class<T> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Time Log Start.");
        long start = System.currentTimeMillis();
        Object object = methodProxy.invokeSuper(o, objects);
        long end = System.currentTimeMillis();
        System.out.println("Time Log End.");
        System.out.println(method.getName() + " cost time :" + (end - start) + ".");
        return object;
    }

}
