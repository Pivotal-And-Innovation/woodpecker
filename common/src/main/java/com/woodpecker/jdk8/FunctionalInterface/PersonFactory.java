package com.woodpecker.jdk8.FunctionalInterface;

/**
 * 测试：：关键字调用构造方法
 *
 * @author Glenn
 * @since 2017-04-06
 */
@SuppressWarnings("all")
public interface PersonFactory<P extends PersonFactoryTest.Person> {
    P create(String firstName, String lastName);
}
