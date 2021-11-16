package com.wangwenjun.concurrent.juc.utils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ConditionExample5
{
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static final LinkedList<Long> list = new LinkedList<>();
    private static final int CAPACITY = 100;
    private static long i = 0;

    private static void produce()
    {
        lock.lock();
        try
        {
            while (list.size() >= CAPACITY)
            {
                condition.await();
            }
            i++;
            list.addLast(i);
            System.out.println(currentThread().getName() + "->" + i);
            condition.signalAll();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }

    private static void consume()
    {
        lock.lock();
        try
        {
            while (list.isEmpty())
            {
                condition.await();
            }
            Long value = list.removeFirst();
            System.out.println(currentThread().getName() + "->" + value);
            condition.signalAll();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }

    private static void sleep()
    {
        try
        {
            TimeUnit.SECONDS.sleep(current().nextInt(5));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
            throws InterruptedException
    {
        IntStream.range(0, 10).forEach(i -> new Thread(() ->
                {
                    for (; ; )
                    {
                        produce();
                        sleep();
                    }
                }, "Producer-" + i).start()
        );
        IntStream.range(0, 5).forEach(i -> new Thread(() ->
                {
                    for (; ; )
                    {
                        consume();
                        sleep();
                    }
                }, "Consumer-" + i).start()
        );
    }
}