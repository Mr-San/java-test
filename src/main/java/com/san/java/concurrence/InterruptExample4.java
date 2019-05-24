package com.san.java.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by San on 2019/5/28.
 * Thread.interrupt会中断Thread.wait
 */
public class InterruptExample4 {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            System.out.println("after pre...");
            wait();
        } catch (InterruptedException e) {
            System.out.println("after catch Exception: " + e);
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final InterruptExample4 example = new InterruptExample4();

        Thread afterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                example.after();
            }
        });

        Thread beforeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                example.before();
            }
        });

        afterThread.start();
        afterThread.interrupt();
        beforeThread.start();
    }
}
