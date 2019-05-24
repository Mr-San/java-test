package com.san.java.concurrence;

/**
 * Created by San on 2019/5/31.
 */
public class WaitNotifyTest {
    public static void main(String args[]) {
        Comm cm = new Comm();
        Thread t1 = new Thread(new DataProducer(cm));
        Thread t2 = new Thread(new DataConsumer(cm));

        // t1 first
        t1.start();
        while (!t1.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t2.start();

        /*// t2 first
        t2.start();
        while (!t2.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t1.start();*/
    }
}

class DataProducer implements Runnable {
    Comm cm;

    DataProducer(Comm c) {
        cm = c;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            cm.produce((int) (Math.random() * 100));
        }

        //try {
        //    for (int i = 1; i <= 5; i++) {
        //        cm.produce((int) (Math.random() * 100));
        //        Thread.sleep(10);
        //    }
        //} catch (InterruptedException e) {
        //    System.out.println("DataProducer 出现异常");
        //}
    }
}

class DataConsumer implements Runnable {
    Comm cm;

    DataConsumer(Comm c) {
        cm = c;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            cm.readout();
        }

        //try {
        //    for (int i = 1; i <= 5; i++) {
        //        cm.readout();
        //        Thread.sleep(10);
        //    }
        //} catch (InterruptedException e) {
        //    System.out.println(" DataConsumer 出现异常");
        //}
    }
}

class Comm {
    private volatile boolean bool = false;// fasle:数据没有准备好，true：数据准备好
    private int n;

    public synchronized void produce(int i) {
        System.out.println(Thread.currentThread() + ": produce");

        if (bool) {
            try {
                System.out.println(Thread.currentThread() + ": produce - wait");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Comm 出现异常");
            }
        }

        n = i;
        System.out.println("产生数据： " + n);
        bool = true;
        notify();   // 唤醒另外一个线程
    }

    public synchronized void readout() {
        System.out.println(Thread.currentThread() + ": readout");

        if (!bool) {
            try {
                System.out.println(Thread.currentThread() + ": readout - wait");
                wait();
            } catch (InterruptedException e) {
                System.out.println(" Comm 出现异常");
            }
        }

        bool = false;
        System.out.println("读取数据： " + n);
        notify();  // 唤醒另一个线程
    }
}
