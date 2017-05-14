package com.woodpecker.jdk8.FunctionalInterface;

/**
 * 测试：：关键字调用构造方法
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public interface PersonFactory<P extends PersonFactoryTest.Person> {
    P create(String firstName, String lastName);
}
