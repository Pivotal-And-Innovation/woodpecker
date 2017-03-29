package com.woodpecker.jvm.dynamic;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * Method Handle 基础用法演示
 *
 * @author Glenn
 * @since 2017-03-27
 */
public class MethodHandleTest {

    private static class ClassA{
        public void println(String string) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object object = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintHM(object).invokeExact("my god.");
    }

    private static MethodHandle getPrintHM(Object object) throws Exception {
        /*代表方法类型，包含了方法的返回值和具体参数，分别对应第一个及后续参数*/
        MethodType type = MethodType.methodType(void.class, String.class);
        /*lookup方法作用是在指定类中查找符合给定的方法名称、方法类型、并且符合调用权限的方法句柄*/
        /*因为这里调用的是一个虚方法，按照java语言惯例，方法的第一个参数是隐式的，代表该方法的接收者，即this指向的对象，该参数以前
        * 放在参数列表中进行传递，现在有bindTo完成*/
        return lookup().findVirtual(object.getClass(), "println", type).bindTo(object);
    }

}
