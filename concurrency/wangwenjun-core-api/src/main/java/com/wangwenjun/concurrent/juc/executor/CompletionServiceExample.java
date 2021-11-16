package com.wangwenjun.concurrent.juc.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class CompletionServiceExample
{
    public static void main(String[] args)
    {
        submitRunnable();
    }

    private static void submitRunnable()
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<AtomicLong> completionService
                = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 5; i++)
        {
            AtomicLong al = new AtomicLong();
            completionService.submit(() ->
            {
                long random = ThreadLocalRandom.current().nextLong(30);
                sleep(random);
                System.out.println("Task " + random + " completed.");
                al.set(random);
            }, al);
        }

        for (int i = 0; i < 5; i++)
        {
            try
            {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    private static void batchTaskByCompletionService()
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService
                = new ExecutorCompletionService<>(executor);
        final List<Callable<Integer>> tasks = Arrays.asList(
                () ->
                {
                    sleep(30);
                    System.out.println("Task 30 completed done.");
                    return 30;
                },
                () ->
                {
                    sleep(10);
                    System.out.println("Task 10 completed done.");
                    return 10;
                },
                () ->
                {
                    sleep(20);
                    System.out.println("Task 20 completed done.");
                    return 20;
                }
        );
        tasks.forEach(completionService::submit);

        for (int i = 0; i < tasks.size(); i++)
        {
            try
            {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    private static void batchTaskDefect()
    {
        ExecutorService executor = Executors.newCachedThreadPool();

        final List<Callable<Integer>> tasks = Arrays.asList(
                () ->
                {
                    sleep(30);
                    System.out.println("Task 30 completed done.");
                    return 30;
                },
                () ->
                {
                    sleep(10);
                    System.out.println("Task 10 completed done.");
                    return 10;
                },
                () ->
                {
                    sleep(20);
                    System.out.println("Task 20 completed done.");
                    return 20;
                }
        );

        try
        {
            List<Future<Integer>> futures = executor.invokeAll(tasks);
            futures.forEach(future ->
            {
                try
                {
                    System.out.println(future.get());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private static void sleep(long seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e)
        {
            //ignore
        }
    }
}
