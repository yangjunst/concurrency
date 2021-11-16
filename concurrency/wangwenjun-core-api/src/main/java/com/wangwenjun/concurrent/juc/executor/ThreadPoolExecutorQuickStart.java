package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.*;

public class ThreadPoolExecutorQuickStart
{
    public static void main(String[] args)
            throws ExecutionException, InterruptedException
    {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        executor.execute(() -> System.out.println(" execute the runnable task"));

        Future<String> future = executor.submit(() -> " Execute the callable task and this is the result");

        System.out.println(future.get());
    }
}
