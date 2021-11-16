package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: SemaphoreDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/18 12:05
 *********************************************/
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                semaphore.acquire(0);
                System.out.println(semaphore.hasQueuedThreads());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.availablePermits());

    }
}
