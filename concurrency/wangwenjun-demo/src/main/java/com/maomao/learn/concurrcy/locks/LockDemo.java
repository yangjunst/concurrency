package com.maomao.learn.concurrcy.locks;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 文件名称: LockDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/28 8:35
 *********************************************/
public class LockDemo {
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--" + lock.hasQueuedThreads() + "-----" + lock.getQueueLength()+lock.isLocked());
                lock.lock();
                lock.unlock();
            });
            t.start();
        } finally {
            lock.unlock();
        }
        try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
