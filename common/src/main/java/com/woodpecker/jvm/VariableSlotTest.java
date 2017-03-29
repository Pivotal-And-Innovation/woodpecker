package com.woodpecker.jvm;

/**
 * 测试局部变量表中的slot重复:会直接影响到系统的垃圾回收行为
 * 建议：如果遇到一个方法，其后面的方法有一些耗时很长的操作，而前面又定义了占用大量内存，实际上已经不会在使用的变量，手动将其设置为null可当做一个特技来使用。
 * 但不推荐：1、编码角度而言，以恰当的变量作用域来控制变量回收时间才是最优雅的解决办法；2、赋null值的操作来优化内存回收是建立在对字节码执行引擎概念模型的理解
 * 之上的。
 *
 * @author Glenn
 * @since 2017-03-21
 */
@SuppressWarnings("unused")
public class VariableSlotTest {

    /**
     * 不回收placeHolder所占用的空间
     */
    private static void caseOne() {
        byte[] placeHolder = new byte[64 * 1024 * 1024];
        System.gc();
    }

    /**
     * 也不回收placeHolder所占用的空间，但经JIT编译器后，赋null值的操作在经过JIT编译优化后会被消除，因此可正常回收内存
     */
    private static void caseTwo() {
        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * 回收placeHolder所占用的空间
     */
    private static void caseThree() {
        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
        int flag = 0;
        System.gc();
    }

    public static void main(String[] args) {
        caseOne();
        caseTwo();
        caseThree();
    }

}
