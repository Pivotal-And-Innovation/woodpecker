package com.woodpecker.jdk8.FunctionalInterface;

import lombok.Data;

/**
 * 测试：：关键字调用构造方法
 *
 * @author Glenn
 * @since 2017-04-06
 */
public class PersonFactoryTest {

    public static void main(String[] args) {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.toString());
    }
    @Data
     static class Person {
        String firstName;
        String lastName;

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
