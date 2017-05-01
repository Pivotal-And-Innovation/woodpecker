package com.woodpecker.jdk8.FunctionalInterface;

/**
 * 测试函数式接口
 *
 * @author Glenn
 * @since 2017-04-06
 */
@SuppressWarnings("all")
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        /**
         * 使用lambda表达式
         */
        Converter<String, Integer> converter1 = from -> Integer.valueOf(from);
        int result1 = converter1.convert("123");
        System.out.println(result1);
        /**
         * 使用：：关键字
         */
        Converter<String, Integer> converter2 = Integer::valueOf;
        int result2 = converter2.convert("123");
        System.out.println(result2);
        /**
         * 通过对象对方法进行调用
         */
        Some some = new FunctionalInterfaceTest.Some() ;
        Converter<String, String> converter3 = some::startsWith;
        String result3 = converter3.convert("Java");
        System.out.println(result3);

        converter3.sayHello();
    }

    private static class Some {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }
}
