package com.woodpecker.jvm.proxy.dynamic.cglib;

import java.util.concurrent.TimeUnit;

/**
 * 被代理类
 *
 * @author Glenn
 * @since 2017-04-12
 */
@SuppressWarnings("all")
public class BeenProxyMain {

    public void grandFather() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am your grandFather.");
    }

    public void grandMother() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am your grandMother.");
    }

    public void father() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am your father.");
    }
}
