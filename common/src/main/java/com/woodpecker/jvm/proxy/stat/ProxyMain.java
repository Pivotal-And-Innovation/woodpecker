package com.woodpecker.jvm.proxy.stat;

/**
 * 代理类
 *
 * @author Glenn
 * @since 2017-04-12
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
