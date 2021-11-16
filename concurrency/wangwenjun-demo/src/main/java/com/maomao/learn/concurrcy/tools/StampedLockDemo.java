package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/********************************************
 * 文件名称: StampedLockDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/25 18:17
 *********************************************/
public class StampedLockDemo {
    private static final StampedLock lock=new StampedLock();
    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(11);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long stamp = lock.tryOptimisticRead();
                System.out.println(lock.validate(stamp));
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                long stamp=-1;
                try {
                    stamp=lock.writeLock();
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    lock.unlockWrite(stamp);
                }
            }
        }.start();
    }
}
