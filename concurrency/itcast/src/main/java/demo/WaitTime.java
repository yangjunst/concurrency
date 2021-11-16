package demo;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/10/19 14:50
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
                 * ��lock.await(timeout)ʱ�����Ȼ��ͷ�����
                 * ������timeoutʱ��ʱ���᳢�Ի�ȡ�����������
                 * ����к����ĳ���������������������ȴ���
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
                     * ��lock.wait(timeout)ʱ�����Ȼ��ͷ�����
                     * ������timeoutʱ��ʱ���᳢�Ի�ȡ�����������
                     * ����к����ĳ���������������������ȴ���
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
