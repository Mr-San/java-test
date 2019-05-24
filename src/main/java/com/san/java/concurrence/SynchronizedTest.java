package com.san.java.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by San on 2019/5/30.
 */
public class SynchronizedTest {
    private A a = new A();

    private class A {
        public void print(){
            synchronized (this) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread() + ": " + i);
                }
            }
        }
    }

    /**
     * 同步一个代码块
     * 它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
     */
    public void func1() {
        synchronized (a) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread() + ": " + i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedTest exampleNew = new SynchronizedTest();

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorService1.execute(() -> exampleNew.func1());
        executorService1.execute(() -> exampleNew.func1());

        //ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.execute(() -> e1.func2());
        //executorService.execute(() -> e1.func2());
    }
}
