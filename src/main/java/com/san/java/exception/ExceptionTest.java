package com.san.java.exception;

/**
 * Created by San on 2019/5/20.
 */
public class ExceptionTest {
    public int testReturn() {
        try {
            System.out.println("try - boday");
            int randomNum = (int)(Math.random() * 10);
            System.out.println(randomNum);
            if (randomNum % 2 == 0) {
                throw new Exception("Defined Exception");
            }
            return 0;// overwrite by finally return
        } catch (Exception e) {
            System.out.println("catch - body");
            return 1;// overwrite by finally return
        } finally {
            System.out.println("finally - body");
            return 2;
        }
    }

    public static void main(String[] args) {
        int result = new ExceptionTest().testReturn();
        System.out.println(result);
    }
}
