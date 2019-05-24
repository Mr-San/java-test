package com.san.java.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by San on 2019/5/30.
 */
public class SynchronizedExample3 {
    /**
     * 同步一个静态方法
     * 作用于整个类，也就是说两个线程调用同一个类的相同对象或不同对象上的这种同步语句，都会进行同步
     */
    public synchronized static void func() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + ": " + i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample3 e1 = new SynchronizedExample3();
        SynchronizedExample3 e2 = new SynchronizedExample3();

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorService1.execute(() -> e1.func());
        executorService1.execute(() -> e1.func());

        ExecutorService executorService2 = Executors.newCachedThreadPool();
        executorService2.execute(() -> e1.func());
        executorService2.execute(() -> e2.func());
    }
}