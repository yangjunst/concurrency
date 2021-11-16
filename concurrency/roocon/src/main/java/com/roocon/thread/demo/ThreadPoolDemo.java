package com.roocon.thread.demo;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/21 11:23
 * Program Goal:
 *********************************************/
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("runnable....");
        });
        Future future=service.submit(()->{
            System.out.println("callable...");
            return "yangjunst";
        });

        Future f=service.submit(()->{
            System.out.println("runnbale...");
        },"y");
        System.out.println("future.get()-->"+f.get());
    }
}
