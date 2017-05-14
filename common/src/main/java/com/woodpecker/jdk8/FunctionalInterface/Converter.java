package com.woodpecker.jdk8.FunctionalInterface;

/**
 * 函数式接口，用注解标识，则只能声明一个抽象的方法声明，但可以添加任意个default的非抽象方法
 *
 * @author Relax
 * @since 2017年03月30日
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);

    default void sayHello() {
        System.out.println("hello.");
    }
}
