package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

public class ThreadPoolExecutorExecution
{
    public static void main(String[] args)
            throws ExecutionException, InterruptedException
    {
        workThreadTimeout();
    }

    private static void workThreadTimeout() throws InterruptedException
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        executor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 15; i++)
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
                System.out.println("Task finish done by " + currentThread());
            });
        }

        TimeUnit.MINUTES.sleep(2);
        assert executor.getActiveCount() == 0;
    }

    private static void execute15TasksAtSameTime()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 15; i++)
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
                System.out.println("Task finish done by " + currentThread());
            });
        }

        executor.shutdown();
    }

    private static void execute14TasksAtSameTime()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 14; i++)
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
                System.out.println("Task finish done by " + currentThread());
            });
        }

        executor.shutdown();
    }

    private static void execute12TasksAtSameTime()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 12; i++)
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
                System.out.println("Task finish done by " + currentThread());
            });
        }

        executor.shutdown();
    }

    private static void firstTimeExecuteTask()
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        executor.execute(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Task finish done.");
        });

        assert executor.getActiveCount() == 1;
        assert executor.getQueue().isEmpty();
        executor.shutdown();
    }
}
