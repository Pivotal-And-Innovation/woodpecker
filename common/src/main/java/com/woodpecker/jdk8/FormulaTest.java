package com.woodpecker.jdk8;

/**
 * 测试接口中默认方法实现
 *
 * @author Relaxier
 * @since 2017年03月30日
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
