package com.san.java.concurrence;

/**
 * Created by San on 2019/5/31.
 */
public class PriorityTest extends Thread {
    @Override
    public void run() {
        try {
            // Thread.sleep不释放锁，释放CPU资源
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread());
    }

    public static void main(String[] args) {
        PriorityTest test1 = new PriorityTest();
        PriorityTest test2 = new PriorityTest();
        PriorityTest test3 = new PriorityTest();

        test1.setPriority(Thread.MIN_PRIORITY);
        test2.setPriority(Thread.NORM_PRIORITY);
        test3.setPriority(Thread.MAX_PRIORITY);

        test1.start();
        test2.start();
        test3.start();
    }
}
