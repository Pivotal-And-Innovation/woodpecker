package com.woodpecker.acm.thread;

/**
 * Thread Demo：两个线程交替打印奇数和偶数
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public class ThreadDemo {

    private int count = 1;

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();

        Thread oddNUm = new Thread(() -> {
            synchronized (demo) {
                while (demo.count % 2 != 0 && demo.count <= 100) {
                    System.out.println("奇数：" + demo.count);
                    demo.count++;
                    demo.notify();
                    if (demo.count == 100) {
                        return;
                    }
                    try {
                        demo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread evenNum = new Thread(() -> {
            synchronized (demo) {
                while (demo.count % 2 == 0 && demo.count <= 100) {
                    System.out.println("偶数：" + demo.count);
                    demo.count++;
                    demo.notify();
                    if (demo.count == 101) {
                        return;
                    }
                    try {
                        demo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        oddNUm.start();
        evenNum.start();
    }

}
