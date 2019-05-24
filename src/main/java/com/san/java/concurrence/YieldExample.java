package com.san.java.concurrence;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by San on 2019/5/30.
 */
public class YieldExample implements Callable<String> {
    private String s;

    public YieldExample() {
    }

    public YieldExample(String s) {
        this.s = s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String call(){
        if(s != null) {
            return s.toUpperCase();
        }
        else{
            Thread.yield();

            return "NULL";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask1 = new FutureTask<String>(new YieldExample());
        Thread thread1 = new Thread(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<String>(new YieldExample("asdfsa"));
        Thread thread2 = new Thread(futureTask2);

        thread1.start();
        thread2.start();

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }
}
