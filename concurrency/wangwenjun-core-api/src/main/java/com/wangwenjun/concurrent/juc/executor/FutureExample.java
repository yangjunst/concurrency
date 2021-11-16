package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.*;

public class FutureExample
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        exception();
    }

    private static void exception()
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(() ->
        {
            throw new RuntimeException();
        });

        try
        {
            System.out.println("The task result: " + future.get());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            System.out.println(e.getCause());
        }
    }

    private static void get() throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return 53.3d;
        });

        System.out.println("The task result: " + future.get());
        System.out.println("The task is done? " + future.isDone());
    }

    private static void quickStart()
            throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return 53.3d;
        });

        System.out.println("main thread do other thing.");
        System.out.println("The task result: " + future.get());
        executor.shutdown();
    }

    private static void cancel() throws InterruptedException, ExecutionException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Task completed.");
            return 53.3d;
        });

        TimeUnit.SECONDS.sleep(10);
//        System.out.println("cancel success ? " + future.cancel(false));
        System.out.println("cancel success ? " + future.cancel(true));
        System.out.println("future is cancelled? " + future.isCancelled());

        System.out.println("Task result:" + future.get());
    }
}
