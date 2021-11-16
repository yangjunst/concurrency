package com.maomao.learn.concurrcy.base;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/28 10:27
 *********************************************/
public class InterruptedExceptionDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("1");
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("2");
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
//        TimeUnit.NANOSECONDS.sleep(1);
        t.interrupt();
    }
}
