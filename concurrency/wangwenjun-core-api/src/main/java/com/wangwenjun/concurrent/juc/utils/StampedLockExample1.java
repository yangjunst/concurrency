package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class StampedLockExample1
{
    private static int shareData = 0;
    private static final StampedLock lock = new StampedLock();

    public static void inc()
    {
        long stamp = lock.writeLock();
        try
        {
            shareData++;
        } finally
        {
            lock.unlockWrite(stamp);
        }
    }

    public static int get()
    {
        long stamp = lock.writeLock();
        try
        {
            return shareData;
        } finally
        {
            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            new Thread(() ->
            {
                while (true)
                {
                    inc();
                    try
                    {
                        TimeUnit.SECONDS.sleep(current().nextInt(5));
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5; i++)
        {
            new Thread(() ->
            {
                while (true)
                {
                    int result = get();
                    System.out.println(currentThread() + "->" + result);
                    try
                    {
                        TimeUnit.SECONDS.sleep(current().nextInt(5));
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}