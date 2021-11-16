package com.wangwenjun.concurrent.juc.executor;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

public class ExecutorAndFuture
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        invokeAll();
    }

    private static void invokeAll()
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            callables.add(() ->
            {
                int random = ThreadLocalRandom.current().nextInt(30);
                TimeUnit.SECONDS.sleep(random);
                System.out.println("Task: " + random + " completed in Thread " + currentThread());
                return random;
            });
        }

        try
        {
            List<Future<Integer>> futures = executor.invokeAll(callables);
            futures.forEach(future ->
            {
                try
                {
                    System.out.println("Result: " + future.get());
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

        executor.shutdown();
    }

    private static void invokeAnyWithTimeout() throws ExecutionException, InterruptedException, TimeoutException
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            callables.add(() ->
            {
                int random = ThreadLocalRandom.current().nextInt(100);
                TimeUnit.SECONDS.sleep(random);
                System.out.println("Task: " + random + " completed in Thread " + currentThread());
                return random;
            });
        }
        Integer result = executor.invokeAny(callables, 100, TimeUnit.MILLISECONDS);
        System.out.println("Result:" + result);
    }

    private static void invokeAny() throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            callables.add(() ->
            {
                int random = ThreadLocalRandom.current().nextInt(30);
                TimeUnit.SECONDS.sleep(random);
                System.out.println("Task: " + random + " completed in Thread " + currentThread());
                return random;
            });
        }
        Integer result = executor.invokeAny(callables);
        System.out.println("Result:" + result);
    }

    private static void submitRunnable() throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicDouble result = new AtomicDouble();
        Future<AtomicDouble> future = executor.submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(20);
                result.set(35.34D);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, result);

        System.out.println("The task result: " + future.get());
        System.out.println("The task is done? " + future.isDone());
    }
}
