package com.san.java.concurrence;

/**
 * Created by San on 2019/5/31.
 */
public class ShareTest {
    public void test() {
        Comm cm = new Comm();
        Thread t1 = new Thread(new DataProducer(cm));
        Thread t2 = new Thread(new DataConsumer(cm));

        // t1 first
        t1.start();
        t1.getState();
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

    public static void main(String args[]) {
        new ShareTest().test();
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
        }
    }

    class Comm {
        private volatile boolean bool = false;// fasle:数据没有准备好，true：数据准备好
        private int n;

        public void produce(int i) {
            System.out.println(Thread.currentThread() + ": produce");

            while(bool){}

            n = i;
            System.out.println("产生数据： " + n);
            bool = true;
        }

        public void readout() {
            System.out.println(Thread.currentThread() + ": readout");

            while(!bool){}

            System.out.println("读取数据： " + n);
            bool = false;
        }
    }
}