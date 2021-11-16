package com.wangwenjun.concurrent.juc.utils;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.concurrent.ThreadLocalRandom.current;

public class PhaserExample1
{
    public static void main(String[] args) throws InterruptedException
    {
        final Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++)
        {
            new Thread(() ->
            {

                try
                {
                    TimeUnit.SECONDS.sleep(current().nextInt(5));
                    phaser.register();
                    int arriveId = phaser.arrive();
                    System.out.println(" completed the work. arriveId:" + arriveId + ",phaser Number:" + phaser.getPhase()+"," +
                            "RegisteredParties:"+phaser.getRegisteredParties()+"ArrivedParties:"+phaser.getArrivedParties());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }, "T-" + i).start();
        }

        phaser.register();
        TimeUnit.SECONDS.sleep(10);
        int arriveId = phaser.arriveAndAwaitAdvance();
        System.out.println(arriveId+"======>"+phaser.getRegisteredParties());
    }
}
