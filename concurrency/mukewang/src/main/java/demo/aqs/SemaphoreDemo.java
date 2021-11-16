package demo.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 19:26
 * Program Goal:
 *********************************************/
public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 10; i++) {
            final int k = i;
            service.execute(() -> {
                boolean acquire = false;
                try {
                    acquire = semaphore.tryAcquire(1, TimeUnit.MILLISECONDS);
                    if(acquire) {
                        test(k);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (acquire) {
                        semaphore.release();
                    }

                }

            });
        }

        System.out.println("finished");
        service.shutdown();
    }

    private static void test(final int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
            System.out.println("i---->" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
