package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: PhaserDemo3.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/27 11:48
 *********************************************/
public class PhaserDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(5) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("onAdvance:--->phase:" + phase + "----registeredParties-->" + registeredParties+"isTerminated--->"+isTerminated());
                return super.onAdvance(phase, registeredParties);
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                phaser.arrive();
                System.out.println(Thread.currentThread().getName() + "--->" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < 5; i++) {
            phaser.arriveAndDeregister();
        }
        System.out.println(Thread.currentThread().getName() + "--->" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());
        System.out.println("===================="+phaser.isTerminated());


        phaser.register();
        phaser.arrive();
        System.out.println(Thread.currentThread().getName() + "--->" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());
    }
}
