package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

public class ReentrantLockExample1
{
    private final Lock lock = new ReentrantLock();

    public void foo()
    {
        lock.lock();
        try
        {
            //...
        } finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        final ReentrantLock lock = new ReentrantLock();
        new Thread(() ->
        {
            lock.lock();
            try
            {
                System.out.println(lock.getHoldCount() == 1);
                TimeUnit.MILLISECONDS.sleep(2);
                lock.lock();
                System.out.println(lock.getHoldCount() == 2);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                lock.unlock();
                System.out.println(currentThread() + " released the lock.");
                lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        lock.lock();
        lock.unlock();
        System.out.println("main finished...");
    }
}