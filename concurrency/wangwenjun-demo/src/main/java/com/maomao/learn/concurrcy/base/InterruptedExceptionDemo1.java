package com.maomao.learn.concurrcy.base;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/18 11:28
 *********************************************/
public class InterruptedExceptionDemo1 {
    public static void main(String[] args) throws InterruptedException {
        /*Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello" + i);
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();*/
        Thread t = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("hello" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
