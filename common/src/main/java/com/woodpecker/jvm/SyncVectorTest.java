package com.woodpecker.jvm;

import java.util.Vector;

/**
 * if one class's methods are decorated by key word {@code synchronized}, don't mean that calling these methods doesn't
 * need any extra synchronized measures any more.
 *
 * @author Glenn
 * @since 2017-03-17
 */
@SuppressWarnings("all")
public class SyncVectorTest {

    private static Vector<Integer> vector = new Vector<>();
    private static Thread rmThread;
    private static Thread prThread;
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            for(int i=0; i<10; i++) {
                vector.add(i);
            }
        }

//        rmThread = new SyncVectorTest().new RmThread();
//
//        prThread = new SyncVectorTest().new PrThread();
//
//        rmThread.start();
//        prThread.start();

        //while (Thread.activeCount() > 20);
    }

    private class RmThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < vector.size(); i++) {
                vector.remove(i);
            }
        }
    }

    private class PrThread extends Thread {
        @Override
        public void run() {
            for(int i=0; i<vector.size(); i++) {
                System.out.println(vector.get(i));
            }
        }
    }

}
