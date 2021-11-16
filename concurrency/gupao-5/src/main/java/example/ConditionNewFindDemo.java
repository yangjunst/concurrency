package example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/7 10:06
 * Program Goal:
 * {@link Condition#await()}{@link Condition#signal()} ()}
 * 与{@link Object#wait()} {@link Object#notify()}的
 * 区别:await被signal操作后，不是立即被唤醒，此时线程仍处于条件等待状态而是只是
 * 由等待队列转移到同步队列，直到遇到lock.unlock操作后，才会进入锁等待状态
 * 才被真正意义上唤醒，接着争取获取锁
 *********************************************/
public class ConditionNewFindDemo {
    private static final Lock lock = new ReentrantLock();
    private static final Condition c = lock.newCondition();
    private static boolean f = false;
    public static void testCondition() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                while (!f) {
                    try {
                        System.out.println("await before....");
                        c.await();
                        System.out.println("await after....");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } finally {
                System.out.println("finally");
                f=false;
                lock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            f = true;
            System.out.println("notify before....");
            c.signal();
            System.out.println("notify after...."+t1.getState());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("notify finally...");
            lock.unlock();
            System.out.println("notify unlock after... "+t1.getState());
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("notify unlock after... "+t1.getState());
        });
        t1.start();
        TimeUnit.MILLISECONDS.sleep(5);
        t2.start();
    }
    public static void main(String[] args) throws InterruptedException {
        testCondition();
        TimeUnit.MILLISECONDS.sleep(15);
    }
/*    public static void testObject() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (c) {
                try {
                    while (!f) {
                        System.out.println("await before....");
                        c.wait(1);
                        System.out.println("await after....");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("finally");
                }
            }
        });
        Thread t2 = new Thread(() -> {
           synchronized (c){

            f = true;
            System.out.println("notify before....");
            c.notify();
            System.out.println("notify after...."+t1.getState());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("notify finally...");
           }
        });
        t1.start();
        TimeUnit.MILLISECONDS.sleep(5);
        t2.start();
    }*/


}
