package com.san.java.concurrence;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<String> {
    private String s;

    public CallableThreadTest() {
    }

    public CallableThreadTest(String s) {
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
            return "NULL";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadTest test = new CallableThreadTest();
        //test.setS("abc");

        FutureTask<String> futureTask = new FutureTask<String>(test);
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println(futureTask.get());
    }
}
