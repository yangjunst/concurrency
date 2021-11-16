package example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/6 11:24
 * Program Goal:
 *********************************************/
public class ConcurrencyToolDemo {
    public static void testSemphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        semaphore.release();
        semaphore.release();
        semaphore.release();
        System.out.println(semaphore.availablePermits());
        semaphore.acquire(4);
        System.out.println("ok");

    }

    public static void testLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        latch.countDown();
        latch.countDown();
        latch.countDown();
        System.out.println(latch.getCount());
        latch.await();

    }

    public static void main(String[] args) throws InterruptedException {
        testSemphore();
        testLatch();
    }
}
