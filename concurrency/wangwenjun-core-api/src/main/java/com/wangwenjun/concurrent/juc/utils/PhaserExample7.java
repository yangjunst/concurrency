package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample7
{
    public static void main(String[] args) throws InterruptedException
    {
/*        final Phaser phaser = new Phaser();
        int phaseID = phaser.register();
        assert phaseID == 0 : "The register phase ID is 0";
        assert phaser.getPhase() == 0 : "The phaser phase ID is 0";
        phaseID = phaser.arrive();
        assert phaseID == 0 : "The phaser arrived phase ID is 0";
        phaseID = phaser.register();
        assert phaseID == phaser.getPhase() && phaseID == 1 : "current phase number is 1";*/


       /* final Phaser phaser = new Phaser(1)
        {
            @Override
            protected boolean onAdvance(int phase, int registeredParties)
            {
                try
                {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return super.onAdvance(phase, registeredParties);
            }
        };
        new Thread(phaser::arrive).start();
        TimeUnit.SECONDS.sleep(2);
        long startTimestamp = System.currentTimeMillis();

        Thread thread = Thread.currentThread();
        new Thread(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            thread.interrupt();
            System.out.println(thread + " is interrupted.");
        }).start();
        int phaseNumber = phaser.register();
        assert phaseNumber == 1 : "current phase number is 1";
        System.out.println("register ELT: " + (System.currentTimeMillis() - startTimestamp));*/


        /*final Phaser phaser = new Phaser(2);
        assert phaser.getPhase() == 0 : "phaser current phase number is 0";
        assert phaser.arrive() == 0 : "arrived phase number is 0";
        assert phaser.arrive() == 0 : "arrived phase number is 0";
        assert phaser.getPhase() == 1 : "phaser current phase number is 1";*/

        /*final Phaser phaser = new Phaser(2);
        assert phaser.arriveAndDeregister() == 0 : "arrived phase number is 0";
        assert phaser.getRegisteredParties() == 1 : "now the register parties is 1";
        assert phaser.getPhase() == 0 : "phaser current phase number is 0";
        assert phaser.arriveAndAwaitAdvance() == 1 : "the next phase number is 1";
        assert phaser.getPhase() == 1 : "phaser current phase number is 1";*/


        final Phaser phaser = new Phaser(1);
        Thread thread = new Thread(() ->
        {
            assert phaser.getPhase() == 0;
            int phaseNumber = phaser.awaitAdvance(phaser.getPhase());
            assert phaseNumber == 1;
        });
        thread.start();
        TimeUnit.MINUTES.sleep(1);

        assert phaser.arriveAndAwaitAdvance() == 1;
        assert phaser.getPhase() == 1;
    }
}
