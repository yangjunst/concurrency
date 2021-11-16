package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.CompletableFuture;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 13:32
 *********************************************/
public class CompletableFutureDemo6 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "JAVA")
                .thenCombine(CompletableFuture.supplyAsync(() -> "SCALA"), (s1, s2) -> s1 + " " + s2);
        future.thenApply(String::toLowerCase)
                .thenAccept(System.out::println);
    }
}
