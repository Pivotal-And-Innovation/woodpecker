package com.woodpecker.jdk8;

/**
 * java8接口中可添加默认的非抽象的方法实现
 *
 * @author Glenn
 * @since 2017-04-06
 */
@SuppressWarnings("all")
public interface Formula {
    double calculate(int arg);

    default double sqrt(int arg) {
        return Math.sqrt(arg);
    }
}
