package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Phaser;

public class PhaserExample8
{
    public static void main(String[] args) throws InterruptedException
    {

        Phaser root = new Phaser(1);
        assertState(root, 0, 1, 1);
        assert root.arrive() == 0;


        Phaser child1 = new Phaser(root, 1);
        Phaser child2 = new Phaser(root, 1);

        assertState(root, 1, 3, 3);
        assertState(child1, 1, 1, 1);
        assertState(child2, 1, 1, 1);
    }

    private static void assertState(Phaser phaser, int phase,
                                    int partites, int unarrived)
    {
        assert phaser.getPhase() == phase;
        assert phaser.getRegisteredParties() == partites;
        assert phaser.getUnarrivedParties() == unarrived;
    }
}
