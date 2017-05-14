package com.woodpecker.jvm.proxy.dynamic.cglib;

/**
 * CGLib动态代理测试
 *
 * @author Relax
 * @since 2017年03月30日
 */
public class CGLibProxyTest {
    public static void main(String[] args) {
        DefinedInterceptor interceptor = DefinedInterceptor.getInstance();
        BeenProxyMain main = interceptor.getProxy(BeenProxyMain.class);

        main.grandFather();
        main.grandMother();
        main.father();
    }
}
