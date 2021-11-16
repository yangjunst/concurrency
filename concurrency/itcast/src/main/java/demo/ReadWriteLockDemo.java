package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/20 21:06
 * Program Goal:
 *********************************************/
public class ReadWriteLockDemo {
    private static final ReadWriteLock rwl = new ReentrantReadWriteLock();

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            Lock lock = rwl.writeLock();
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "----->write...." + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        readLockBeforeWriteLock();
//        writeLockBeforeReadLock();
//        doubleWriteLock();
        doubleReadLock();
    }

    /**
     * 先读后写。读执行，写阻塞
     * read lock start...
     */
    public static void readLockBeforeWriteLock() {
        new Thread(() -> {
            rwl.readLock().lock();
            System.out.println("read lock start...");
            rwl.writeLock().lock();
            System.out.println("write lock start....");
            rwl.writeLock().unlock();
            rwl.readLock().unlock();
        }).start();
    }
    /**
     * 先写后读。写执行，读也执行
     * write lock start....
     * read lock start...
     */
    public static void writeLockBeforeReadLock() {
        new Thread(()->{
            rwl.writeLock().lock();
            System.out.println("write lock start....");
            rwl.readLock().lock();
            System.out.println("read lock start...");
            rwl.readLock().unlock();
            rwl.writeLock().unlock();
        }).start();
    }

    /**
     * 先写后写。双写执行
     * write lock start....
     * write lock start...
     */
    public static void doubleWriteLock() {
        new Thread(()->{
            rwl.writeLock().lock();
            System.out.println("write lock start....");
            rwl.writeLock().lock();
            System.out.println("write lock start...");
            rwl.writeLock().unlock();
            rwl.writeLock().unlock();
        }).start();
    }
    public static void doubleReadLock() {
        new Thread(()->{
            rwl.readLock().lock();
            System.out.println("read lock start....");
            rwl.readLock().lock();
            System.out.println("read lock start...");
            rwl.readLock().unlock();
            rwl.readLock().unlock();
        }).start();
    }



    /**
     * 写锁在多线程环境下的测试
     */
        public static void main4 () {
            for (int i = 0; i < 10; i++) {
                new Thread(new MyRunnable(), "t" + i).start();
            }
        }

        /**
         * 同一线程内，写锁重入
         */
        public static void main3 () {
            Lock lock2 = rwl.writeLock();
            Lock lock3 = rwl.writeLock();
            lock3.lock();
            System.out.println("write lock start...");
            lock2.lock();
            System.out.println("write lock start");
            lock2.unlock();
            lock3.unlock();
        }
        /**
         * read lock and write lock can't run at the same time;
         */
        public static void main2 () {
            Lock lock = rwl.readLock();
            Lock lock2 = rwl.writeLock();
            Lock lock3 = rwl.writeLock();
            lock.lock();
            System.out.println("read lock start...");
            lock2.lock();
            System.out.println("write lock start");
            lock2.unlock();
            lock.unlock();


        }

        /**
         * ReadWriteLock can repeat get the same read lock or write lock
         */
        public static void main1 () {
            Lock lock = rwl.readLock();
            Lock lock1 = rwl.readLock();
            Lock lock2 = rwl.writeLock();
            Lock lock3 = rwl.writeLock();
            System.out.println(lock == lock1);
            System.out.println(lock2 == lock3);


        }
    }
