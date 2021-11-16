package com.wangwenjun.concurrency.chapter11;

import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/24 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class  ThreadException {
    private final static int A = 10;
    private final static int B = 0;


    public static void main(String[] args) throws InterruptedException {

        new Test1().test();

       /* Thread t = new Thread(() -> {
            try {
                Thread.sleep(5_000L);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
       t.setUncaughtExceptionHandler((tt,e)->{
           System.out.println(tt.getName());
           System.out.println(e.getClass());
       });*/
//        t.start();
    }
}