package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/23 13:28
 *********************************************/
public class FutureDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Float> future= service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1.0f;
        });

        System.out.println("get information....");
        System.out.println(future.get());
        System.out.println("over...");
        service.shutdown();
    }
}
