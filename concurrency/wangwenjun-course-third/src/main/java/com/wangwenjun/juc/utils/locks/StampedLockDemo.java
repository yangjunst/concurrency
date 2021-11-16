package com.wangwenjun.juc.utils.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/10 10:42
 * Program Goal:
 *********************************************/
public class StampedLockDemo {
    private static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            final int k = i;
            new Thread(() -> {
                while (true) {
                    try {
                        read();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t-" + k).start();
        }

        TimeUnit.SECONDS.sleep(5);
        new Thread(() -> {
            write();
        }).start();
    }

    public static void read() throws InterruptedException {
        long stamp = lock.tryOptimisticRead();
        System.out.println("stamp--->" + stamp + "--->" + lock.validate(stamp));
        if (!lock.validate(stamp)) {
            try {
                stamp = lock.readLock();
                System.out.println(Thread.currentThread().getName() + "--->read");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        }
        TimeUnit.MILLISECONDS.sleep(800);

    }

    public static void write() {
        long stamp = -1;
        try {
            stamp = lock.writeLock();
            System.out.println(Thread.currentThread().getName() + "--->write");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
