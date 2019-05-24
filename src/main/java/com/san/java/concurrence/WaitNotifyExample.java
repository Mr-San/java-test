package com.san.java.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by San on 2019/5/28.
 */
public class WaitNotifyExample {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            System.out.println("after pre...");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final WaitNotifyExample example = new WaitNotifyExample();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example.before();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example.after();
            }
        });
    }
}
