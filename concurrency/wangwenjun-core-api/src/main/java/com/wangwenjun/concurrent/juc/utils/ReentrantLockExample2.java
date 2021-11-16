package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

public class ReentrantLockExample2
{
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    private static void m1()
    {
        lock1.lock();
        System.out.println(currentThread() + " get lock1.");
        try
        {
            lock2.lock();
            System.out.println(currentThread() + " get lock2.");
            try
            {
                //...
            } finally
            {
                lock2.unlock();
                System.out.println(currentThread() + " release lock2.");
            }
        } finally
        {
            lock1.unlock();
            System.out.println(currentThread() + " release lock1.");
        }
    }

    private static void m2()
    {
        lock2.lock();
        System.out.println(currentThread() + " get lock2.");
        try
        {
            lock1.lock();
            System.out.println(currentThread() + " get lock1.");
            try
            {
                //...
            } finally
            {
                lock1.unlock();
                System.out.println(currentThread() + " release lock1.");
            }
        } finally
        {
            lock2.unlock();
            System.out.println(currentThread() + " release lock2.");
        }
    }

    public static void main(String[] args)
    {
        new Thread(() ->
        {
            while (true)
            {
                m1();
            }
        }).start();
        new Thread(() ->
        {
            while (true)
            {
                m2();
            }
        }).start();
    }
}