package com.woodpecker.jvm.proxy.stat;

/**
 * 代理类
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class ProxyMain {

    /**
     * 持有被代理类的引用
     */
    private BeenProxyMain beenProxyMain = new BeenProxyMain();

    public void sayHello() {
        beenProxyMain.sayHello();
    }

    public void sayByeBye() {
        beenProxyMain.sayByeBye();
    }
}
