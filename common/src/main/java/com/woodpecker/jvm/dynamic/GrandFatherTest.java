package com.woodpecker.jvm.dynamic;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * 子类调用祖类方法演示
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("unused")
public class GrandFatherTest {

    private class GrandFather {
        void thinking() {
            System.out.println("I am grandFather.");
        }
    }

    private class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("I am father.");
        }
    }

    private class Son extends Father {
        /**
         * 实现调用祖父类的thinking方法,JDk1.7之前很难代码实现，但可直接生成字节码解决。现在可利用如下代码解决.....悲剧了。结果对不上
         */
        @Override
        void thinking() {
            try {
                MethodType type = MethodType.methodType(void.class);
                MethodHandle handle = lookup().findSpecial(GrandFather.class, "thinking", type, getClass());
                handle.invoke(this);
            } catch (Throwable throwable) {
                System.out.println("UNKNOWN ERROR!");
            }
        }
    }

    public static void main(String[] args) {
        (new GrandFatherTest().new Son()).thinking();
    }

}
