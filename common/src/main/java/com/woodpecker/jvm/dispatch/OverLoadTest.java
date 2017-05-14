package com.woodpecker.jvm.dispatch;

import java.io.Serializable;

/**
 * 演示编译器如何确定一个更加合适的重载版本
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("unused")
public class OverLoadTest {

    private static void sayHello(Object arg) {
        System.out.println("hello. Object!");
    }

    private static void sayHello(int arg) {
        System.out.println("hello. Int!");
    }

    private static void sayHello(long arg) {
        System.out.println("hello. Long!");
    }

    private static void sayHello(char arg) {
        System.out.println("hello. Char!");
    }

    private static void sayHello(Character arg) {
        System.out.println("hello. Character!");
    }

    private static void sayHello(Serializable arg) {
        System.out.println("hello. Serializable!");
    }

    /**
     * 可变长参数的重载优先级最低
     */
    private static void sayHello(char... arg) {
        System.out.println("hello. Char...");
    }

    private static void sayHello(int... arg) {
        System.out.println("hello. Int...");
    }

    private static void sayHello(Character... arg) {
        System.out.println("hello. Character...");
    }

    private static void sayHello(Object... arg) {
        System.out.println("hello. Object...");
    }

    /**
     * 查看输出，层层注释掉本次调用的方法，再查看输出。即可观察到重载的本质
     */
    public static void main(String[] args) {
        sayHello('a');
    }

}
