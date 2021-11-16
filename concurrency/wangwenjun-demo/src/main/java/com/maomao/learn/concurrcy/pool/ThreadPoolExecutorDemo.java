package com.maomao.learn.concurrcy.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: ThreadPoolExecutorDemi.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/23 8:49
 *********************************************/
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(2);
        executor.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("sleep...");
            } catch (InterruptedException e) {
                System.out.println("sleep");
                e.printStackTrace();
            }
        });
        executor.shutdown();
        while (!executor.awaitTermination(1,TimeUnit.HOURS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池已经关闭");
    }
}
