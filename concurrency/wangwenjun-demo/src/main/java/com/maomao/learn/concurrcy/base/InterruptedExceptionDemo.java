package com.maomao.learn.concurrcy.base;

import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: InterruptedExceptionDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/29 9:43
 *********************************************/
public class InterruptedExceptionDemo extends Thread {

    @Override
    public void run() {
        MyRunnable myRunnable = new MyRunnable();
        Thread t = new Thread(() -> {
            try {
//                    TimeUnit.SECONDS.sleep(1);
                TimeUnit.MILLISECONDS.sleep(1);
                myRunnable.run();
            } catch (InterruptedException e) {
                System.err.println("This Sleep Exception...");
            }
        });
        t.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }

    public static void main(String[] args) {
        new InterruptedExceptionDemo().start();
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("MyRunnable Exception...");

            }
        }
    }
}
