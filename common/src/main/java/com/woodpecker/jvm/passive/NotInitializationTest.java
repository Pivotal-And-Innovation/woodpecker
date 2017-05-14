package com.woodpecker.jvm.passive;

/**
 * 1、通过子类引用父类的静态字段，不会导致子类初始化
 * 结论：对于静态字段，只有直接定义这个字段的类才会初始化因此通过其子类引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
 *
 * 2、通过数组定义引用类，不会触发此类的初始化
 *
 * 3、常量在编译阶段会存入调用类的常量池，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。其实在编译阶段通过常量传播优化
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("unused")
public class NotInitializationTest {

    public static void main(String[] args) {
        /*1*/
        System.out.println(subMain.value);

        /*2*/
        SuperMain[] main = new SuperMain[10];

        /*3*/
        System.out.println(ConstMain.HELLO_WORLD);
    }

}
