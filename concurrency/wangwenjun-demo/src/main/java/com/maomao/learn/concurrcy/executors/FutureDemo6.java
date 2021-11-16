package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/18 10:19
 *********************************************/
public class FutureDemo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service= Executors.newCachedThreadPool();
        Future<?> submit = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("run over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("start");
        submit.get();
        System.out.println("end");

    }
}
