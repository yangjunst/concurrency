package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: PhaserDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/26 9:46
 *********************************************/
public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(5){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println(phase+"<-->"+registeredParties);
                if(phase==3)
                return true;
                return false;
            }
        };
        while (true){
            for(int i=0;i<5;i++){
                new Thread(phaser::arriveAndAwaitAdvance).start();
            }
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(phaser.isTerminated()+"<==>"+phaser.getPhase());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Integer.MIN_VALUE);
        }

    }
}
