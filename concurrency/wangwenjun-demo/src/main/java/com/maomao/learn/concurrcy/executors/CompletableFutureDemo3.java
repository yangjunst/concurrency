package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 11:01
 *********************************************/
public class CompletableFutureDemo3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("supply-->" + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                },service)
                .thenApply(e -> {
                    System.out.println("apply--->" + Thread.currentThread().getName());
                    return e.length();
                });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
