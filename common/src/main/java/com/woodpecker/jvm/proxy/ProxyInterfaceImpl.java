package com.woodpecker.jvm.proxy;

/**
 * @author Glenn
 * @since 2017-03-27
 */
class ProxyInterfaceImpl implements ProxyInterface {
    @Override
    public void doSomething() {
        System.out.println("do something...");
    }
}
