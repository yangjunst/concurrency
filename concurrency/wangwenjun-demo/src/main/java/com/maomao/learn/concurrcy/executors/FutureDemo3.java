package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/23 15:11
 *********************************************/
public class FutureDemo3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(() -> {
            int a = 1 / 0;
            return "hello world";
        });
        try {
            TimeUnit.SECONDS.sleep(3);
            future.get();
        } catch (InterruptedException e) {
            System.out.println("-->ok2");
            System.out.println(e.getClass());
        } catch (ExecutionException e) {
            System.out.println("-->ok3");
            System.out.println(e.getClass());
            System.out.println(e.getCause());
        } catch (CancellationException e) {
            System.out.println("-->ok4");
            System.out.println(e.getClass());
        }
        service.shutdown();
    }
}
