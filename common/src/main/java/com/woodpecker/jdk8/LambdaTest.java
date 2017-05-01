package com.woodpecker.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda相关测试：链表排序
 *
 * @author Glenn
 * @since 2017-04-06
 */
@SuppressWarnings("all")
public class LambdaTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("peter", "anna", "mike", "xenia", "tom");
        /**
         * 使用匿名Comparator对象
         */
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        list.forEach(System.out::println);
        /**
         * 一步一步简化
         */
        Collections.sort(list, (String o1, String o2) -> {
            return o2.compareTo(o1);
        });

        Collections.sort(list, (String o1, String o2) -> o2.compareTo(o1));

        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
    }

}
