package com.woodpecker.jvm.proxy.stat;

/**
 * 演示静态代理：被代理类，代理类与被代理类不必须实现统一接口，代理类在程序运行前已经存在的代理方式称为静态代理。
 * 由开发人员编写或是编译器生成代理类的方式都属于静态代理。
 *
 * 1、隐藏委托类的实现<br>
 * 2、解耦，不改变委托类代码情况下做一些额外处理，比如添加初始判断及其他公共操作
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class BeenProxyMain {

    public void sayHello() {
        System.out.println("@Hello...");
    }

    public void sayByeBye() {
        System.out.println("@ByeBye...");
    }

    public void sayMyGod() {
        System.out.println("@Oh My God...");
    }
}
