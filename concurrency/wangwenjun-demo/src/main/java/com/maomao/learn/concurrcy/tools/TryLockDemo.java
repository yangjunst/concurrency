package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 文件名称: TryLockDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/18 11:11
 *********************************************/
public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        lock.tryLock(6,TimeUnit.SECONDS);
        System.out.println("main finished...");
    }

}
