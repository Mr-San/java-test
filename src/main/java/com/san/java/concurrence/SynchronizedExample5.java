package com.san.java.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by San on 2019/5/30.
 * Q：同一个类中多个同步方法（非静态方法和静态方法组合）？
 * G：同一类只有一个锁，一个线程进入某一个synchronized中，其他线程都必须在所有synchronized外阻塞
 * A：同时调用方法锁或同时调用类锁同时只会有一个线程获得锁，同时访问一个方发锁和类锁的两个线程不会互斥，可以同时获得锁
 */
public class SynchronizedExample5 {
    /**
     * 同步一个代码块
     * 它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread() + "-1: " + i);
            }
        }
    }

    /**
     * 同步一个非静态方法
     * 它和同步代码块一样，作用于同一个对象。
     */
    public synchronized void func2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + "-2: " + i);
        }
    }

    /**
     * 同步一个类
     * 作用于整个类，也就是说两个线程调用同一个类的相同对象或不同对象上的这种同步语句，都会进行同步
     */
    public void func3() {
        synchronized (SynchronizedExample5.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread() + "-3: " + i);
            }
        }
    }

    /**
     * 同步一个静态方法
     * 作用于整个类，也就是说两个线程调用同一个类的相同对象或不同对象上的这种同步语句，都会进行同步
     */
    public synchronized static void func4() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread() + "-4: " + i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample5 e1 = new SynchronizedExample5();
        SynchronizedExample5 e2 = new SynchronizedExample5();

        /*ExecutorService executorService1 = Executors.newCachedThreadPool();
        //executorService1.execute(() -> e1.func1());
        //executorService1.execute(() -> e1.func1());
        //executorService1.execute(() -> e1.func2());
        //executorService1.execute(() -> e1.func2());
        executorService1.execute(() -> e1.func3());
        executorService1.execute(() -> e1.func4());
        executorService1.execute(() -> e2.func3());
        executorService1.execute(() -> e2.func4());
        executorService1.execute(() -> e1.func4());
        executorService1.execute(() -> e1.func4());
        executorService1.execute(() -> e2.func4());
        executorService1.execute(() -> e2.func4());*/

        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //executorService2.execute(() -> e1.func1());
        //executorService2.execute(() -> e1.func1());
        //executorService2.execute(() -> e1.func2());
        //executorService2.execute(() -> e1.func2());
        executorService2.execute(() -> e1.func3());
        executorService2.execute(() -> e1.func3());
        executorService2.execute(() -> e2.func3());
        executorService2.execute(() -> e2.func3());
        executorService2.execute(() -> e1.func4());
        executorService2.execute(() -> e1.func4());
        executorService2.execute(() -> e2.func4());
        executorService2.execute(() -> e2.func4());
        executorService2.execute(() -> e1.func3());
        executorService2.execute(() -> e1.func3());
        executorService2.execute(() -> e2.func3());
        executorService2.execute(() -> e2.func3());
        executorService2.execute(() -> e1.func4());
        executorService2.execute(() -> e1.func4());
        executorService2.execute(() -> e2.func4());
        executorService2.execute(() -> e2.func4());

        /*ExecutorService executorService1 = Executors.newCachedThreadPool();
        //executorService1.execute(() -> e1.func1());
        //executorService1.execute(() -> e1.func1());
        //executorService1.execute(() -> e1.func2());
        //executorService1.execute(() -> e1.func2());
        executorService1.execute(() -> e1.func3());
        executorService1.execute(() -> e1.func3());
        executorService1.execute(() -> e2.func3());
        executorService1.execute(() -> e2.func3());
        executorService1.execute(() -> e1.func4());
        executorService1.execute(() -> e1.func4());
        executorService1.execute(() -> e2.func4());
        executorService1.execute(() -> e2.func4());*/
    }
}