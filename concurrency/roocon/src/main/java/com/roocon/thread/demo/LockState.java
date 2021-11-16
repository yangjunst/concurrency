package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/25 13:35
 * Program Goal:
 * LockSupport.park()  Lock.await()         Object.wait()  WAITING
 * LockSupport.park(111)  Lock.await(111)   Object.wait(111)  TIMED_WAITING
 * synchronized  BLOCKED
 *********************************************/
public class LockState implements Runnable{
    Lock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        LockState state=new LockState();
        Thread thread1 = new Thread() {
            @Override
            public  void run() {
                state.run();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public  void run() {
                state.run();
            }
        };

        thread1.start();
        TimeUnit.SECONDS.sleep(1);

        thread2.start();
        TimeUnit.SECONDS.sleep(1);

        System.out.println(thread2.getState());
    }

    @Override
    public synchronized void run() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
