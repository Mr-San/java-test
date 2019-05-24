package com.san.java.concurrence;

/**
 * Created by San on 2019/5/30.
 * Thread.interrupt会中断Thread.join
 */
public class InterruptExample2 {
    private class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " run ThreadA");
        }
    }

    private class ThreadB extends Thread {
        private ThreadA a;

        public ThreadB(ThreadA a) {
            this.a = a;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " run ThreadB pre ...");

            try {
                // 当前线程调用另一个线程的Thread.join方法，导致当前线程进入阻塞状态；当另一个线程执行完成后当前线程再继续执行
                a.join();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + ": catch Exception - " + e);
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread() + " run ThreadB");
        }
    }

    public void test() {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB(a);
        b.start();
        b.interrupt();
        a.start();
    }

    public static void main(String[] args) {
        InterruptExample2 example = new InterruptExample2();
        example.test();
    }
}
