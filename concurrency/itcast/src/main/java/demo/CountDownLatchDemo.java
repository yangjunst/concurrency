package demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/21 17:52
 * Program Goal:
 *********************************************/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);

        latch.countDown();
        latch.await();
        latch.await();
        latch.await();
        latch.await();
        System.out.println(latch.getCount());
        latch.countDown();
        System.out.println(latch.getCount());
        System.out.println("ok");
    }
    public static void main1() throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);
        new Thread(()->{
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName()+"---> wake up...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName()+"---> wake up...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        TimeUnit.SECONDS.sleep(1);
        latch.countDown();
        System.out.println("countdown first done...");
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName()+"---> wake up...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();
        TimeUnit.SECONDS.sleep(1);
        latch.countDown();
        System.out.println("countdown second done...");

    }
}
