package demo;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/19 14:50
 * Program Goal:
 * {@link Object#wait(long)}
 *********************************************/

public class WaitTime {
    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();
    private static final Condition c = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        testAwaitTime();
    }

    public static void testAwaitTime() throws InterruptedException {
        Thread t1 = new Thread(() -> {

            try {
                lock.lock();
                System.out.println("wait before....");
                /**
                 * 当lock.await(timeout)时，首先会释放锁。
                 * 当到了timeout时间时，会尝试获取锁，获得锁，
                 * 则进行后续的程序操作，否则阻塞在锁等待上
                 */
                c.await(2, TimeUnit.SECONDS);
                System.out.println("wait over...");
                TimeUnit.MILLISECONDS.sleep(3_000);
                System.out.println("sleep over...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t2 get lock...");
                System.out.println("t1 state--->" + t1.getState());
                TimeUnit.SECONDS.sleep(10);
                System.out.println("t1 state--->" + t1.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("lock release....");
            }
        });
        t2.start();
    }

    public static void testWaitTime() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println("wait before....");
                    /**
                     * 当lock.wait(timeout)时，首先会释放锁。
                     * 当到了timeout时间时，会尝试获取锁，获得锁，
                     * 则进行后续的程序操作，否则阻塞在锁等待上
                     */
                    LOCK.wait(2_000);
                    System.out.println("wait over...");
                    TimeUnit.MILLISECONDS.sleep(3_000);
                    System.out.println("sleep over...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                System.out.println("t2 get lock...");
                try {
                    System.out.println("t1 state--->" + t1.getState());
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("t1 state--->" + t1.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock release....");
            }
        });
        t2.start();
    }
}
