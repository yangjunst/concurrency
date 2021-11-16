package demo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/21 17:08
 * Program Goal:
 * {@link Semaphore#tryAcquire(long, TimeUnit)}
 * 与
 * {@link Lock#tryLock(long, TimeUnit)}效果相似
 *********************************************/
public class SemphoreDemo {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "--->acquire shared source...");
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        service.shutdown();
    }

    public static void main() {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                boolean f = false;
                try {
                    f = semaphore.tryAcquire();
                    System.out.println(Thread.currentThread().getName() + "--->acquire shared source...");
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (f) {
                        semaphore.release();
                        System.out.println("--->release shared source..." + Thread.currentThread().getName());
                    }

                }
            });
        }
        service.shutdown();

    }
}
