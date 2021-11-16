package com.maomao.learn.concurrcy.tools;


import java.util.concurrent.*;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/********************************************
 * 文件名称: CyclicBarrierDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/17 11:50
 *********************************************/

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(11, () -> {
            System.out.println("ok");
        });
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            service.execute(() -> {
                int time = current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(time);
                    if (j == 5) {
                        System.out.println(currentThread().getName() + "-->" + time + "s before");
                        barrier.await(50, TimeUnit.MILLISECONDS);
                        System.out.println(currentThread().getName() + "-->" + time + "s after");
                    } else {
                        barrier.await();
                    }
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                }
            });
        }
        int await = 0;
        try {
            await = barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
        }
        System.out.println("main--->" + await);
        System.out.println(currentThread().getName() + " first finished");
    }
}