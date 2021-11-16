package com.maomao.learn.concurrcy.tools;

import java.sql.Time;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: PhaserTest.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/27 10:10
 *********************************************/
public class PhaserTest {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        for (int i = 0; i < 50; i++) {
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                phaser.register();
                int arrive = phaser.arrive();
                System.out.println(arrive + "---" + phaser.getPhase() + "---" + phaser.getRegisteredParties() + "---" + phaser.getArrivedParties() + "---" + phaser.getUnarrivedParties());
            }).start();
        }
    }
}
