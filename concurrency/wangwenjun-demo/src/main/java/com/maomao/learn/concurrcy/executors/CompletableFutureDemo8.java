package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.CompletableFuture;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/25 13:48
 *********************************************/
public class CompletableFutureDemo8 {
    public static void main(String[] args) {
        CompletableFuture.<String>supplyAsync(()->{throw new RuntimeException("");})
                .handle((r,e)->{
                    if(e!=null){
                        return "ERROR";
                    }else{
                        return r;
                    }
                }).thenAccept(System.out::println);

    }
}
