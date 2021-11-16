package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/19 13:16
 * Program Goal:
 * {@link Thread.State}NEW RUNNABLE WAITING TIMED_WAITING BLOCKED    TERMINATED
 *********************************************/
public class ThreadState {
    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                while (true) {
                }
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(t1.getState());//RUNNABLE
        Thread t2 = new Thread(() -> {
            try{
                lock.lock();
                System.out.println("ok");
            }finally {
                lock.unlock();
            }
        });
        t2.start();
        TimeUnit.NANOSECONDS.sleep(1);
        System.out.println(t2.getState());//WAITING
    }

    public static void main() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                while (true) {
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(t1.getState());//RUNNABLE
        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                System.out.println("ok");
            }
        });
        t2.start();
        TimeUnit.NANOSECONDS.sleep(1);
        System.out.println(t2.getState());//BLOCKED
    }

    public static void main1() {
        Thread t = new Thread(() -> {
            while (true) {
            }
        });
        System.out.println(t.getState());//NEW
        t.start();
        System.out.println(t.getState());//RUNNABLE
    }
}
