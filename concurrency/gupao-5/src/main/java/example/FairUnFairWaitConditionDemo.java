package example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/4 2:24
 * Program Goal:
 *********************************************/
public class FairUnFairWaitConditionDemo {
    private static final Object o = new Object();
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    /**
     * 4->2->3-1
     */

    public static void main(String[] args) throws InterruptedException {
        waitMain();
        TimeUnit.SECONDS.sleep(1);
        synchronized (o){
            o.notify();
            o.notify();
            o.notify();
            o.notify();
        }
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        conditionMain();
        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        condition.signal();
        condition.signal();
        condition.signal();
        condition.signal();
        lock.unlock();

    }

    private static void waitFunc() {
        synchronized (o) {
            try {
                System.out.println(Thread.currentThread().getName() + "--->wait start...");
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--->wait finish...");
        }

    }

    private static void condition() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "--->await start...");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "--->await finish...");
            lock.unlock();
        }


    }

    public static void waitMain() throws InterruptedException {
        new Thread(() -> {
            waitFunc();
        }, "t4").start();
//        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            waitFunc();
        }, "t2").start();
//        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            waitFunc();
        }, "t3").start();
//        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            waitFunc();
        }, "t1").start();

    }

    public static void conditionMain() throws InterruptedException {

        new Thread(() -> {
            condition();
        }, "t4").start();
//        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            condition();
        }, "t2").start();
//        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            condition();
        }, "t3").start();
//        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            condition();
        }, "t1").start();

    }
}
