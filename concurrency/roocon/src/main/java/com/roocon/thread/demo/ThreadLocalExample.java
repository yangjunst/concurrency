package com.roocon.thread.demo;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/25 15:03
 * Program Goal:
 *********************************************/
public class ThreadLocalExample {
    private static final ThreadLocal<Date> local = ThreadLocal.withInitial(
            () -> {
                System.out.println(Thread.currentThread().getName());
             return   new Date();
            }    );

    public static void main(String[] args) {
        IntStream.rangeClosed(1,10).forEach((e)->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            new Thread(()->{
                System.out.println(local.get().toString());
            }).start();
        });
    }
}
