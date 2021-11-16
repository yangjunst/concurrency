package com.wangwenjun.concurrent.juc.executor;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ScheduledThreadPoolExecutorExample
{
    public static void main(String[] args)
            throws ExecutionException, InterruptedException
    {
        scheduleFixedDelayVsFixedRate();
    }

    private static void scheduleFixedDelayVsFixedRate()
    {
        ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(2);
//        Runnable command = () ->
//        {
//            long startTimestamp = System.currentTimeMillis();
//            System.out.println("current timestamp: " + startTimestamp);
//            try
//            {
//                TimeUnit.MILLISECONDS.sleep(current().nextInt(100));
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//            System.out.println("elapsed time: " + (System.currentTimeMillis() - startTimestamp));
//        };
//
//        scheduleExecutor.scheduleWithFixedDelay(command, 10, 1000, TimeUnit.MILLISECONDS);

        scheduleExecutor.scheduleAtFixedRate(() ->
        {
            System.out.println(currentThread() + " : " + new Date());
            try
            {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, 10, 1000, TimeUnit.MILLISECONDS);
    }

    private static void scheduleWithFixedDelay() throws ExecutionException, InterruptedException
    {
        ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = scheduleExecutor.scheduleWithFixedDelay(
                () -> System.out.println(new Date()),
                10, 60, TimeUnit.SECONDS);
        System.out.println(future.get());
    }

    private static void scheduleAtFixedRate() throws ExecutionException, InterruptedException
    {
        ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = scheduleExecutor.scheduleAtFixedRate(
                () -> System.out.println(new Date()),
                10, 60, TimeUnit.SECONDS);
        System.out.println(future.get());
//        new Thread(()->{
//            while(true){
//                System.out.println(future.isDone());
//                System.out.println(future.isCancelled());
//                System.out.println("===================================");
//                try
//                {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    private static void oneShotScheduleReturnFuture() throws ExecutionException, InterruptedException
    {
        ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<String> future = scheduleExecutor.schedule(() ->
        {
            System.out.println("I am running");
            return "Hello";
        }, 10, TimeUnit.SECONDS);

        System.out.println("result: " + future.get());
    }


    private static void oneShotScheduleReturnVoidFuture() throws ExecutionException, InterruptedException
    {
        ScheduledThreadPoolExecutor scheduleExecutor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = scheduleExecutor.schedule(() ->
        {
            System.out.println("I am running");
        }, 10, TimeUnit.SECONDS);

        System.out.println("result: " + future.get());
    }
}
