package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 11:31
 *********************************************/
public class CompletableFutureDemo4 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        CompletableFuture
                .supplyAsync(()->{
                    System.out.println("Supply-->"+Thread.currentThread().getName());
                    return "Hello World";},service)
                .thenAcceptAsync((e)->{
                    System.out.println("Accept-->"+Thread.currentThread().getName());
                    System.out.println(e.length());
                }).thenRunAsync(()-> System.out.println("All of task complete...."+Thread.currentThread().getName()));
        service.shutdownNow();
    }
}
