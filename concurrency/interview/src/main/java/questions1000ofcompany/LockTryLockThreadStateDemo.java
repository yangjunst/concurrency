package questions1000ofcompany;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/27 15:25
 * Program Goal:
 *********************************************/
public class LockTryLockThreadStateDemo {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock.tryLock(20,TimeUnit.SECONDS);//TIMED_WAITING
                condition.await(1,TimeUnit.MINUTES);//TIMED_WAITING
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                lock.tryLock(20,TimeUnit.SECONDS);//TIMED_WAITING

                condition.await();//WAITING
                TimeUnit.SECONDS.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                lock.tryLock(20,TimeUnit.SECONDS);//TIMED_WAITING
                condition.await();//WAITING
                TimeUnit.SECONDS.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                lock.lock();
                condition.signalAll();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * t1--->WAITING
         * t2--->WAITING
         * t3--->WAITING
         * t4--->TERMINATED
         */
        System.out.println("t1--->" + t1.getState());
        System.out.println("t2--->" + t2.getState());
        System.out.println("t3--->" + t3.getState());
        System.out.println("t4--->" + t4.getState());
    }
}
