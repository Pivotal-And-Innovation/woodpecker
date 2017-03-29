package com.woodpecker.jvm.passive;

/**
 * @author Glenn
 * @since 2017-03-23
 */
class ConstMain {
    static {
        System.out.println("ConstMain init.");
    }

    static final String HELLO_WORLD = "hello world.";
}
