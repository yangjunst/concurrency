package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorMethod
{
    public static void main(String[] args)
    {
        preStart();
    }

    private static void preStart()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        assert executor.getActiveCount() == 0;

        assert executor.prestartAllCoreThreads() == 2;

        assert executor.getPoolSize() == 2;
    }

    private static void stats()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        new Thread(() ->
        {
            while (true)
            {
                System.out.println(executor.getTaskCount());
                System.out.println(executor.getCompletedTaskCount());
                System.out.println(executor.getLargestPoolSize());
                System.out.println("====================================");
                try
                {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        for (; ; )
        {
            executor.execute(() ->
            {
                try
                {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }
    }
}
