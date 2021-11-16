package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 9:55
 *********************************************/
public class CompletableFutureDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Double> completableFuture=new CompletableFuture<>();
        ExecutorService service= Executors.newCachedThreadPool();
        service.submit(()->{
            TimeUnit.SECONDS.sleep(5);
            completableFuture.cancel(false);
            return completableFuture.complete(12.3);
        });
        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("enter...");
            System.out.println(completableFuture.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (CancellationException e){
            System.out.println(e.getClass());
        }


    }
}
