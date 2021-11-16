package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Phaser;

public class PhaserExample4
{
    public static void main(String[] args)
    {
        final Phaser phaser = new Phaser(2)
        {
            @Override
            protected boolean onAdvance(int phase, int registeredParties)
            {
                return phase >= 1;
            }
        };
        System.out.println(phaser.getPhase());
        phaser.arrive();
        phaser.arrive();
        assert phaser.getPhase() == 1 : "so far, the phase number is 1.";
        assert !phaser.isTerminated() : "phaser is not terminated.";
        System.out.println("---->"+phaser.getPhase());
        phaser.arrive();
        phaser.arrive();
        System.out.println("---->"+(phaser.getPhase()+Integer.MAX_VALUE));
        phaser.arrive();
        phaser.arrive();
        System.out.println("---->"+(phaser.getPhase()+Integer.MAX_VALUE));
        assert phaser.getPhase() < 0 : "so far, the phase number is negative value.";
        assert phaser.isTerminated() : "phaser is terminated now.";

        //invoke below method will not work.
        phaser.arriveAndAwaitAdvance();
    }
}