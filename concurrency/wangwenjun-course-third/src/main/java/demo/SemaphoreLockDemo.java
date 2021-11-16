package demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 16:08
 * Program Goal:不支持可重入锁功能
 *********************************************/
public class SemaphoreLockDemo {

    final static SemaphoreLock lock = new SemaphoreLock();

    public static void f1() throws InterruptedException {
        lock.lock();
        System.out.println("f1...getLock...");
        f2();
        lock.unlock();
        System.out.println("f1...releaseLock...");
    }

    public static void f2() throws InterruptedException {
        lock.lock();
        System.out.println("f2...getLock...");
        lock.unlock();
        System.out.println("f2...releaseLock...");
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 5; i++)
            new Thread(() -> {
                try {
                    semaphore.drainPermits();
                    System.out.println(Thread.currentThread().getName() + "-->"+semaphore.availablePermits());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(1);
                }
            }).start();
    }

    static class SemaphoreLock {

        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
