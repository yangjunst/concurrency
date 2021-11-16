package com.wangwenjun.concurrent.juc.executor;

import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

public class ExecutorServiceShutdown
{
    public static void main(String[] args)
            throws InterruptedException
    {
        shutdownBestPractice();
    }

    private static void shutdownBestPractice()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++)
        {
            executor.execute(() ->
            {
                try
                {
                    System.out.println(currentThread() + " is running.");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }
        shutdownAndAwaitTermination(executor, 5, TimeUnit.MINUTES);
    }

    private static void shutdownAndAwaitTermination(ExecutorService executor, long timeout, TimeUnit unit)
    {
        executor.shutdown();
        try
        {
            if (!executor.awaitTermination(timeout, unit))
            {
                executor.shutdownNow();
                if (!executor.awaitTermination(timeout, unit))
                {
                    //print executor not terminated by normal.
                }
            }
        } catch (InterruptedException e)
        {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static void shutdownNow()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++)
        {
            executor.execute(() ->
            {
                try
                {
                    System.out.println(currentThread() + " is running.");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }

        List<Runnable> remainingRunnable = executor.shutdownNow();
        System.out.println(remainingRunnable.size());
    }

    private static void shutdown() throws InterruptedException
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++)
        {
            executor.execute(() ->
            {
                try
                {
                    System.out.println(currentThread() + " is running.");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        assert executor.isShutdown();
        assert executor.isTerminating();
        assert !executor.isTerminated();

        executor.execute(() -> System.out.println("new task submit after shutdown"));

        executor.awaitTermination(10, TimeUnit.MINUTES);

        assert executor.isShutdown();
        assert !executor.isTerminating();
        assert executor.isTerminated();
    }
}
