package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

public class ThreadPoolExecutorFactory
{

    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 5; i++)
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
    }

    private static class MyThreadFactory implements ThreadFactory
    {

        private final static String PREFIX = "ALEX";
        private final static AtomicInteger INC = new AtomicInteger();

        @Override
        public Thread newThread(Runnable command)
        {
            ThreadGroup group = new ThreadGroup("MyPool");
            Thread thread = new Thread(group, command, PREFIX + "-" + INC.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    }
}
