package com.woodpecker.jdk8;

/**
 * 测试接口中默认方法实现
 *
 * @author Glenn
 * @since 2017-04-06
 */
public class FormulaTest {

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int arg) {
                return sqrt(arg * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(100));
    }

}
