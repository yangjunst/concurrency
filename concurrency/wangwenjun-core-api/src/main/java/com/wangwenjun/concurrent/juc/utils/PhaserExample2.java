package com.wangwenjun.concurrent.juc.utils;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class PhaserExample2
{
    public static void main(String[] args)
            throws InterruptedException
    {
        final Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++)
        {
            new Thread(() ->
            {
                phaser.register();
                try
                {
                    TimeUnit.SECONDS.sleep(current().nextInt(20));
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(new Date() + ":" + currentThread() + " completed the work.");
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }, "T-" + i).start();
        }

        TimeUnit.SECONDS.sleep(10);

        phaser.register();
        phaser.arriveAndAwaitAdvance();
        assert phaser.getRegisteredParties() == 11 : "total 11 parties is registered.";
        System.out.println(new Date() + ": all of sub task completed work.");
    }
}
