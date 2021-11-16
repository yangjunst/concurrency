package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

public class PhaserExample3 {
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "---->phase:" + phase+"-->isTerminated:"+isTerminated());
                return true;
            }
        };
            for (int i = 0; i < 10; i++) {
                new Thread(() ->
                {
                    phaser.register();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                        phaser.arrive();
                        System.out.println(phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "T-" + i).start();
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println("begin");
        int register = phaser.register();
        System.out.println("register:"+register);


    }
}