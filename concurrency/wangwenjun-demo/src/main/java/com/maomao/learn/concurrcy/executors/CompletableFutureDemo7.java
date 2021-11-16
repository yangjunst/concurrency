package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 13:36
 *********************************************/
public class CompletableFutureDemo7 {
    public static void main(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "JAVA";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "SCALA";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "KOTLIN";
        });
        CompletableFuture<Void> future1 = CompletableFuture.allOf(f1, f2, f3).thenRun(() -> {
            try {
                System.out.println(f1.isDone() + "-->r-->" + f1.get());
                System.out.println(f2.isDone() + "-->r-->" + f2.get());
                System.out.println(f3.isDone() + "-->r-->" + f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture future2 = CompletableFuture.anyOf(f1, f2, f3).thenRun(() -> {
            try {
                System.out.println(f1.isDone() + "->result-->" + f1.get());
                System.out.println(f2.isDone() + "->result-->" + f2.get());
                System.out.println(f3.isDone() + "->result-->" + f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        try {
            future1.get();
            future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
