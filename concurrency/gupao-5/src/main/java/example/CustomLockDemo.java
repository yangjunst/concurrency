package example;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/3 14:23
 * Program Goal:
 *********************************************/
class MyLock implements Lock {

    private boolean isLocked = false;
    private int lockCount = 0;
    private Thread lockedBy = null;

    @Override
    public synchronized void lock() {
        if (isLocked && lockedBy != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        lockCount++;
    }

    @Override
    public synchronized void unlock() {
        if (lockedBy == Thread.currentThread()) {
            lockCount--;
            if (lockCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}

public class CustomLockDemo {

    ReentrantLock reentrantLock;
    ReadWriteLock readWriteLock;
    ReentrantReadWriteLock reentrantReadWriteLock;

    CountDownLatch countDownLatch;
    CyclicBarrier cyclicBarrier;
    Semaphore semaphore;
    ArrayBlockingQueue arrayBlockingQueue;
    LinkedBlockingQueue linkedBlockingQueue;
    SynchronousQueue queue;
    PriorityBlockingQueue priorityBlockingQueue;
    DelayQueue delayQueue;
    LinkedTransferQueue linkedTransferQueue;



    private static MyLock lock = new MyLock();

    public static void main(String[] args) {
        a();
    }

    private static void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    private static void b() {
        lock.lock();
        System.out.println("b");
        c();
        lock.unlock();
    }

    private static void c() {
        lock.lock();
        System.out.println("c");
        lock.unlock();
    }
}
