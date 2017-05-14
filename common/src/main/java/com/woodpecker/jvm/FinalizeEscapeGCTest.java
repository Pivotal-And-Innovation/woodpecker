/*
 * www.beebank.com Inc.
 * Copyright (c) 2017年03月30日 All Rights Reserved.
 */

package com.woodpecker.jvm;

/**
 * finalize()演示垃圾回收逃逸和回收
 *
 * <p>1.一个对象当要被回收的时候可以逃逸.<br>
 * 2.一个对象只能有一次机会逃逸，因为finalize最多只能被系统调用一次.
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public class FinalizeEscapeGCTest {

    private static FinalizeEscapeGCTest SAVE_HOOK = null;

    private void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method finalize executed...");
        FinalizeEscapeGCTest.SAVE_HOOK = this;
    }

    public static void main(String args[]) {
        SAVE_HOOK = new FinalizeEscapeGCTest();
        /*the object escape at first*/
        SAVE_HOOK = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (SAVE_HOOK == null) {
            System.out.println("no, i am dead :(");
        } else {
            SAVE_HOOK.isAlive();
        }

        /*the object escape for twice failed*/
        SAVE_HOOK = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (SAVE_HOOK == null) {
            System.out.println("no, i am dead :(");
        } else {
            SAVE_HOOK.isAlive();
        }
    }

}
