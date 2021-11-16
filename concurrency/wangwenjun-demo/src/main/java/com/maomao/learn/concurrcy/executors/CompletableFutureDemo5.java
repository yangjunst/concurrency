package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.CompletableFuture;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 11:45
 *********************************************/
public class CompletableFutureDemo5 {
    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(() -> "JAVA")
                .thenCompose(s->CompletableFuture.supplyAsync(()->s+" SCALA"))
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);

        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Yang")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Jun"));
        future.thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }
}
