package example;


import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/29 16:11
 * Program Goal:
 * {@link LockSupport#parkNanos(long)}
 * {@link LockSupport#parkUntil(long)} }
 * {@link Thread#interrupt()} can 中断{@link LockSupport#park()} 且无异常抛出
 *********************************************/
public class LockSupportDemo {
    private static final Lock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t1);
    }
    public static void test2() throws InterruptedException {
        Thread t1=new Thread(()->{
            lock.lock();
            try {
                System.out.println("sleep start...");
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        Thread t2=new Thread(()->{
            System.out.println("get lock start...");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"--->get lock...");
            lock.unlock();
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t2);
    }
    public static void test() throws InterruptedException {
        Thread t=new Thread(()->{
            try{
                LockSupport.park();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println("ok");
            }

        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
