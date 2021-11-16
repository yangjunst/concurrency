package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample4
{
    public static void main(String[] args)
            throws InterruptedException
    {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        new Thread(() ->
        {
            lock.lock();
            try
            {
                condition.await();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);

        try
        {
            lock.hasWaiters(condition);
            assert false : "should not process to here.";
        } catch (Exception e)
        {
            assert e instanceof IllegalMonitorStateException;
        }

        try
        {
            lock.getWaitQueueLength(condition);
            assert false : "should not process to here.";
        } catch (Exception e)
        {
            assert e instanceof IllegalMonitorStateException;
        }

        lock.lock();
        try
        {
            assert lock.hasWaiters(condition);
            assert lock.getWaitQueueLength(condition) == 1;
        } finally
        {
            lock.unlock();
        }
    }
}