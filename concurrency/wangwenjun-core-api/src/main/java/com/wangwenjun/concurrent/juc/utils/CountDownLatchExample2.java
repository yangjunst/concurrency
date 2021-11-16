package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.ThreadLocalRandom.current;

public class CountDownLatchExample2
{
    public static void main(String[] args)
            throws InterruptedException
    {
        final AtomicBoolean projectInitializeDown = new AtomicBoolean(false);
        final CountDownLatch latch = new CountDownLatch(1);
        System.out.println("start initialize the idea.");
        new Thread(new ProjectInitializeTask(latch, projectInitializeDown)).start();
        initIdea();
        latch.await();

        //do the other thing
        if (projectInitializeDown.get())
        {
            System.out.println("all of initialize phase finished.");
        } else
        {
            System.out.println("The initialize failed due to project can't be initialize");
        }
    }

    private static void initIdea()
    {
        try
        {
            TimeUnit.SECONDS.sleep(current().nextInt(10));
            System.out.println("idea plugins initialize successfully");
        } catch (InterruptedException e)
        {
        }
    }

    private static class ProjectInitializeTask implements Runnable
    {
        private final CountDownLatch latch;
        private final AtomicBoolean projectInitializeDown;

        private ProjectInitializeTask(CountDownLatch latch,
                                      AtomicBoolean projectInitializeDown)
        {
            this.latch = latch;
            this.projectInitializeDown = projectInitializeDown;
        }

        @Override
        public void run()
        {
            try
            {
                TimeUnit.SECONDS.sleep(current().nextInt(10));
                System.out.println("the project initialize successfully");
                projectInitializeDown.set(true);
            } catch (InterruptedException e)
            {
            } finally
            {
                latch.countDown();
            }
        }
    }
}