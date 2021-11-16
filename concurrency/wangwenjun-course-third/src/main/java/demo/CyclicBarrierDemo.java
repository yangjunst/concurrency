package demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/9 8:10
 * Program Goal:
 *********************************************/
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("callback-->" + Thread.currentThread().getName());
//            Thread.currentThread().interrupt();
        });

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "start...");
                try {

                    if ("t-2".equals(Thread.currentThread().getName())) {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        barrier.reset();
                    }
                    if ("t-1".equals(Thread.currentThread().getName())) {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }if ("t-0".equals(Thread.currentThread().getName())) {
                        TimeUnit.MILLISECONDS.sleep(1500);
                    }
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()+"isInterrupted-->"+Thread.currentThread().isInterrupted());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "-------->" + e.getClass() + ";msg-->" + e.getMessage());
                } catch (BrokenBarrierException e) {
                    System.out.println(Thread.currentThread().getName() + "--------->" + e.getClass());
                }
                System.out.println(Thread.currentThread().getName() + "end...");
            }, "t-" + i).start();
        }
    }
}
