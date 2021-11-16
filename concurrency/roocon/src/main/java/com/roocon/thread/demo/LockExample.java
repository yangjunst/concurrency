package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/23 10:36
 * Program Goal:
 *********************************************/
public class LockExample {
    private Lock lock = new ReentrantLock();

    private void show() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "-->lock enter...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "-->sleep exception...");
            }
            System.out.println(Thread.currentThread().getName() + "-->lock outer...");
            lock.unlock();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "-->lock exception...");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LockExample example = new LockExample();
        Thread t1 = new Thread(() -> example.show(), "t1");
        Thread t2 = new Thread(() -> example.show(), "t2");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
//        t1.interrupt();
        t2.interrupt();
    }
}
