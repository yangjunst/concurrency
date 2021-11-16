package com.maomao.learn.concurrcy.locks;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/28 10:47
 *********************************************/
public class WaitNotifyDemo1 {
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (WaitNotifyDemo1.class){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WaitNotifyDemo1.class.notify();
                System.out.println("notify over...");
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (WaitNotifyDemo1.class){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("wait begin...");
                    WaitNotifyDemo1.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("wait over...");
            }
        }).start();
    }
}
