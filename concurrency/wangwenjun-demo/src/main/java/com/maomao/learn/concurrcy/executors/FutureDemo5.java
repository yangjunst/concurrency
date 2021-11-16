package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 16:59
 *********************************************/
public class FutureDemo5 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(() -> {
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("over");
            return 111;
        });
        TimeUnit.MILLISECONDS.sleep(1);
        future.cancel(true);
        try {
            Object o = future.get();
            System.out.println(o);
        } catch (Exception e){
            e.printStackTrace();
        }
        service.shutdown();
    }
}
