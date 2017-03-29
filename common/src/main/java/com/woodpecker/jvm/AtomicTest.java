package com.woodpecker.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic test
 *
 * @author Glenn
 * @since 2017-03-16
 */
public class AtomicTest {

    private static final int THREAD_COUNT = 20;
    private static AtomicInteger race = new AtomicInteger(0);
    //private static int race = 0;

    private static void increase() {
        race.incrementAndGet();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    increase();
                    //race++;
                }
            });

            threads[i].start();
        }

//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }

        System.out.println(race.get());
    }

}
