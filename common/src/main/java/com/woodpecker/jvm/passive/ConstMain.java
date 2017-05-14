package com.woodpecker.jvm.passive;

/**
 * @author Relax
 * @since 2017年03月30日
 */
class ConstMain {
    static {
        System.out.println("ConstMain init.");
    }

    static final String HELLO_WORLD = "hello world.";
}
