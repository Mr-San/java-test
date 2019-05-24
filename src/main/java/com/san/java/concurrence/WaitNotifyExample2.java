package com.san.java.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by San on 2019/5/28.
 */
public class WaitNotifyExample2 {
    private int count = 0;

    public void countAutoAdd(){
        count ++;
        System.out.println(Thread.currentThread() + ": " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final WaitNotifyExample2 example = new WaitNotifyExample2();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (example) {
                    System.out.println(Thread.currentThread() + ": Do somthing for count < 10");

                    while(example.getCount() < 10) {
                        example.countAutoAdd();
                    }

                    try {
                        example.notify();
                        example.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread() + ": Do somthing for count > 10");
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (example){
                    for(int i = 0; i < 100; i ++){
                        System.out.println(Thread.currentThread() + ": " + i);
                    }

                    while(example.getCount() != 10) {
                        try {
                            example.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if(example.getCount() == 10){
                        System.out.println(Thread.currentThread() + ": Do somthing for count = 10");
                        example.countAutoAdd();
                        example.notify();
                    }
                }
            }
        });
    }
}
