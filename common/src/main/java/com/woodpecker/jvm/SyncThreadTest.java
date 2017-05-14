/*
 * www.beebank.com Inc.
 * Copyright (c) 2017年03月30日 All Rights Reserved.
 */

package com.woodpecker.jvm;

/**
 * Thread 测试
 *
 * @author Relax
 * @since 2017年03月30日
 */
public class SyncThreadTest {

    private static volatile boolean isStop;
    private static int count = 0;

    public static void main(String args[]) throws InterruptedException {
        Thread holdThread = new Thread(() -> {
            while (!isStop) {
                count++;
            }
        });

        holdThread.start();
        Thread.sleep(300);
        //TimeUnit.SECONDS.sleep(3);
        isStop = true;
        System.out.println(count);
    }

}
