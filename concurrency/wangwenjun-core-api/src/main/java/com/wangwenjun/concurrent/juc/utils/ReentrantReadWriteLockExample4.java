package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample4
{
    public static void main(String[] args) throws InterruptedException
    {
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        final Lock readLock = readWriteLock.readLock();
        new Thread(() ->
        {
            readLock.lock();
            try
            {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                readLock.unlock();
            }

        }).start();
        TimeUnit.SECONDS.sleep(1);

        readLock.lock();
        assert readWriteLock.getReadLockCount() == 2;
        System.out.println("main thread can hold the read lock still.");
        readLock.unlock();

    }

}