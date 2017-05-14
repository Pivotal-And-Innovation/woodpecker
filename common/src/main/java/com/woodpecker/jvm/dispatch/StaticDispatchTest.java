package com.woodpecker.jvm.dispatch;

/**
 * 方法静态分派演示
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("unused")
public class StaticDispatchTest {

    static abstract class Human {

    }

    private static class Man extends Human {
    }

    private static class Woman extends Human {
    }

    private void sayHello(Human human) {
        System.out.println("hello, human!");
    }

    public void sayHello(Man man) {
        System.out.println("hello, man!");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello, woman!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatchTest item = new StaticDispatchTest();
        item.sayHello(man);
        item.sayHello(woman);
    }

}
