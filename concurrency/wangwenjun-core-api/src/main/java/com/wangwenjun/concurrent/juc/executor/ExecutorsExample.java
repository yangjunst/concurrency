package com.wangwenjun.concurrent.juc.executor;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class ExecutorsExample
{
    public static void main(String[] args) throws InterruptedException
    {
        testSingleThreadPool();
    }

    private static void testSingleThreadPool() throws InterruptedException
    {
        singleThreadPool();
        printThreadStack();
        System.out.println("*************************************");
        System.gc();
        TimeUnit.MINUTES.sleep(1);
        printThreadStack();
    }

    private static void printThreadStack()
    {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] ids = threadMXBean.getAllThreadIds();
        for (long id : ids)
        {
            System.out.println(threadMXBean.getThreadInfo(id));
        }
    }

    private static void singleThreadPool()
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("normal task."));
    }

    private static void newFixedExecutor() throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //
        for (int i = 0; i < 4; i++)
            executorService.execute(
                    () ->
                    {
                        System.out.println("normal task." + currentThread());
                        try
                        {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
            );

        shutdownAndAwaitTermination(executorService, 1, TimeUnit.MINUTES);
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
            currentThread().interrupt();
        }
    }
}
