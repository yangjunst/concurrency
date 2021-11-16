package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/18 22:53
 * Program Goal:
 * 1,double unlock
 * 2,tryLock(TimeUnit)
 * 3,lockInterrupted
 *********************************************/
public class LockDemo {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        main6();
    }

    public static void main6() {
        new Thread(()->{
            boolean b =false;
            try {
                b = lock.tryLock(3,TimeUnit.SECONDS);
                System.out.println("try lock a --->"+b);
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(b){
                    lock.unlock();
                    System.out.println("release a lock...");
                }
            }


        }).start();
        new Thread(()->{
            boolean b =false;
            try {
                b = lock.tryLock(3,TimeUnit.SECONDS);
                System.out.println("try lock b --->"+b);
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(b){
                    lock.unlock();
                    System.out.println("release b lock...");
                }
            }


        }).start();
    }

    /**
     * {@link Lock#tryLock()}尝试获取
     * 锁不成功，也会执行后面的语句
     */
    public static void main5() throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        boolean b = false;
        try {
            b = lock.tryLock();
            System.out.println(b);
        } finally {
            if (b) {
                lock.unlock();
            }
        }
    }

    /**
     * lock.lockInterruptibly();
     * 在某线程在获取lock未得到时，
     * 进行阻塞，那么该获取锁的方式，可以被中断。
     * java.lang.InterruptedException
     * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireInterruptibly(AbstractQueuedSynchronizer.java:1220)
     * at java.util.concurrent.locks.ReentrantLock.lockInterruptibly(ReentrantLock.java:335)
     */
    public static void main4(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(20);
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
                lock.lockInterruptibly();
                System.out.println("get lock...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t2.start();
        t2.interrupt();
    }

    /**
     * {@link Lock#tryLock(long, TimeUnit)}
     * 结合着boolean lockSuccess变量酌情unlock
     */

    public static void main3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("lock unlock...");
                lock.unlock();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> {
            boolean lockSuccess = false;
            try {
                lockSuccess = lock.tryLock(2, TimeUnit.SECONDS);
                System.out.println("get lock..."+lockSuccess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lockSuccess) {
                    lock.unlock();
                }
            }
        });

        t2.start();
        TimeUnit.SECONDS.sleep(1);
//        t2.interrupt();
    }


    /**
     * lock and unlock in different thread
     * Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
     * at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
     * at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
     * at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
     */
    public static void main2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            lock.unlock();
        });
        t2.start();
    }

    /**
     * double unlock
     * Exception in thread "main" java.lang.IllegalMonitorStateException
     * at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
     * at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
     * at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
     */
    public static void main1() {
        try {
            lock.lock();
            System.out.println("ok");
        } finally {
            lock.unlock();

            lock.unlock();
        }
    }
}
