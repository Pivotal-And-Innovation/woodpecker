package com.woodpecker.jvm;

/**
 * 静态语句块中只能访问定义在其之前的变量，定义在其后的变量，只能赋值，不能访问
 *
 * @author Glenn
 * @since 2017-03-16
 */
public class IllegalForwardTest {
    static {
        index = 1;
        //System.out.println(index);
    }
    static int index;

    public static void main(String[] args) {

    }

}
