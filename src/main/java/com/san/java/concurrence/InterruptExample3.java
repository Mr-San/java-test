package com.san.java.concurrence;

/**
 * Created by San on 2019/5/30.
 * Thread.interrupt不会中断Sync
 */
public class InterruptExample3 {
    /**
     * 同步一个代码块
     * 它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread() + ": " + i);
            }
        }
    }

    /**
     * 同步一个非静态方法
     * 它和同步代码块一样，作用于同一个对象。
     */
    public synchronized void func2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread() + ": " + i);
        }
    }

    public static void main(String[] args) {
        InterruptExample3 e1 = new InterruptExample3();

        Thread threadA = new Thread(() -> e1.func1());
        Thread threadB = new Thread(() -> e1.func1());

        threadA.start();
        threadA.interrupt();
        threadB.start();

        //ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.execute(() -> e1.func2());
        //executorService.execute(() -> e1.func2());
    }
}
