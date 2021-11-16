package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample3
{
    public static void main(String[] args)
            throws BrokenBarrierException, InterruptedException
    {
        final CyclicBarrier barrier = new CyclicBarrier(3);
        Thread thread = new Thread(() ->
        {
            try
            {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(10);

        thread.interrupt();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(barrier.isBroken());

        barrier.await();

    }
}