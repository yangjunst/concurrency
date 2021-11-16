package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

public class CyclicBarrierExample2
{
    public static void main(String[] args)
            throws BrokenBarrierException, InterruptedException
    {
        final CyclicBarrier barrier = new CyclicBarrier(11);
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Tourist(i, barrier)).start();
        }
        barrier.await();
        System.out.println("Tour Guider:all of Tourist get on the bus.");
//        barrier.reset();
        barrier.await();
        System.out.println("Tour Guider:all of Tourist get off the bus.");
    }

    private static class Tourist implements Runnable
    {
        private final int touristID;
        private final CyclicBarrier barrier;

        private Tourist(int touristID, CyclicBarrier barrier)
        {
            this.touristID = touristID;
            this.barrier = barrier;
        }

        @Override
        public void run()
        {
            System.out.printf("Tourist:%d by bus\n", touristID);
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist:%d Get on the bus, and wait other people reached.\n");
            System.out.printf("Tourist:%d arrival the destination\n", touristID);
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist:%d Get off the bus, and wait other people get off.\n");
        }

        private void waitAndPrint(String message)
        {
            System.out.printf(message, touristID);
            try
            {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e)
            {
            }
        }

        private void spendSeveralSeconds()
        {
            try
            {
                TimeUnit.SECONDS.sleep(current().nextInt(10));
            } catch (InterruptedException e)
            {
            }
        }
    }
}