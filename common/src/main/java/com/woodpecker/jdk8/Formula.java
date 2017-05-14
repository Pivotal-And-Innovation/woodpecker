package com.woodpecker.jdk8;

/**
 * java8接口中可添加默认的非抽象的方法实现
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public interface Formula {
    double calculate(int arg);

    default double sqrt(int arg) {
        return Math.sqrt(arg);
    }
}
