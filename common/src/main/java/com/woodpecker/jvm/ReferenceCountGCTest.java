package com.woodpecker.jvm;

/**
 * 演示JVM没有通过计算计数算法来判断对象的存活：解决相互引用问题
 *
 * @author Glenn
 * @since 2017-04-05
 */
@SuppressWarnings("all")
public class ReferenceCountGCTest {

    private ReferenceCountGCTest instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 该属性存在的意义是占用内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountGCTest A = new ReferenceCountGCTest();
        ReferenceCountGCTest B = new ReferenceCountGCTest();

        A.instance = B;
        B.instance = A;

        A = null;
        B = null;

        // 该处触发垃圾回收：观察GC日志变化
        System.gc();
    }

}
