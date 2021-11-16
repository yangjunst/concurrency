package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: CountDownLatchDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/17 10:53
 *********************************************/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }.start();
            latch.await();
            latch.await();
            latch.await();
            latch.await();
        System.out.println("ok");
    }
}
