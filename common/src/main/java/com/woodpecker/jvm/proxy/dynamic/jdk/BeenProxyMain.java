package com.woodpecker.jvm.proxy.dynamic.jdk;

import java.util.concurrent.TimeUnit;

/**
 * 被代理类实现接口
 *
 * @author Relax
 * @since 2017年03月30日
 */
public class BeenProxyMain implements ProxyInterface {
    @Override
    public void grandFather() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am your grandFather.");
    }

    @Override
    public void grandMother() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am your grandMother.");
    }

    @Override
    public void father() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am your father.");
    }
}
