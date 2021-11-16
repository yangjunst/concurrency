package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

public class ConditionExample3 {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->
            {
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println(currentThread().getName() + " is waked up.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
        TimeUnit.SECONDS.sleep(1);
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}