package com.woodpecker.jvm.proxy.stat;

/**
 * 静态代理测试
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class StaticProxyTest {
    /**
     * 持有代理类的引用
     */
    private ProxyMain proxyMain = new ProxyMain();

    public void sayHello() {
        proxyMain.sayHello();
    }

    public void sayByeBye() {
        proxyMain.sayByeBye();
    }
    public static void main(String[] args) {
        StaticProxyTest test = new StaticProxyTest();
        test.sayHello();
        test.sayByeBye();
    }

}
