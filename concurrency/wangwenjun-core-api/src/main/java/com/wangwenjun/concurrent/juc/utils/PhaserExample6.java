package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Phaser;

public class PhaserExample6
{
    public static void main(String[] args)
            throws InterruptedException
    {
        final Phaser phaser = new Phaser(3);
        assert phaser.getPhase() == 0 : "current phase number is 0";

        phaser.arrive();
        phaser.arrive();
        phaser.arrive();
        assert phaser.getPhase() == 1 : "current phase number is 1";
        phaser.bulkRegister(1);
        phaser.arrive();
        phaser.arrive();
        phaser.arrive();
        phaser.arrive();
        assert phaser.getPhase() == 2 : "current phase number is 2";
    }
}
