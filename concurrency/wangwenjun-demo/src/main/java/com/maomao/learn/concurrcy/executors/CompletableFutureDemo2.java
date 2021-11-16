package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 10:15
 *********************************************/
public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "yangjunst");
        try {
            System.out.println(future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "yangjunst-service", service);
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        CompletableFuture.runAsync(()-> System.out.println("runnable"));
        CompletableFuture.runAsync(()-> System.out.println("runnable-service"),service);
    }
}
