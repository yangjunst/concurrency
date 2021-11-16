package example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/3 13:48
 * Program Goal:
 * synchronized--unfair
 * lock--fair
 *********************************************/
public class FairUnFairLockSycnDemo {
    private static final Object o = new Object();
    private static final Lock lock=new ReentrantLock();

    /**
     * 4->2->3-1
     */

    public static void main(String[] args) throws InterruptedException {
        syncMain();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        lockMain();

    }
    private static void sync() {
        System.out.println(Thread.currentThread().getName() + "--->start...");
        synchronized (o) {
            try {
                TimeUnit.MILLISECONDS.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--->finish...");
        }

    }
    private static void lock() {
        System.out.println(Thread.currentThread().getName() + "--->start...");
            try {
                lock.lock();
                TimeUnit.MILLISECONDS.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "--->finish...");
                lock.unlock();
            }


    }
    public static void syncMain() throws InterruptedException {
        sync();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(() -> {
            sync();
        }, "t4").start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            sync();
        }, "t2").start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            sync();
        }, "t3").start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            sync();
        }, "t1").start();

    }
    public static void lockMain() throws InterruptedException {
        lock();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            lock();
        }, "t4").start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            lock();
        }, "t2").start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            lock();
        }, "t3").start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            lock();
        }, "t1").start();

    }
}
