/*
 * www.beebank.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */

package com.woodpecker.jvm;

import java.util.concurrent.TimeUnit;

/**
 * Thread 测试
 *
 * @author Glenn
 * @since 2017-03-14
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
        TimeUnit.SECONDS.sleep(30);
        isStop = true;
        System.out.println(count);
    }

}
