package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.Phaser;

/********************************************
 * 文件名称: PhaserDemo2.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/27 13:13
 *********************************************/
public class PhaserDemo2 {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(2);
        System.out.println(Thread.currentThread().getName() + "--->" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());
        phaser.arriveAndDeregister();
        System.out.println(Thread.currentThread().getName() + "--->" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + "--->" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "----" + phaser.getUnarrivedParties());

        System.out.println();
    }
}
