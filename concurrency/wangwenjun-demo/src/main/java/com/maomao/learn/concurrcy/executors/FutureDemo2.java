package com.maomao.learn.concurrcy.executors;

import java.io.IOException;
import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/23 13:42
 *********************************************/
public class FutureDemo2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(() -> {

            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try{
//                TimeUnit.SECONDS.sleep(5);
//                throw new IllegalArgumentException("hello illegal...");
//            }catch (InterruptedException e){
//                System.out.println("ok1");
//                System.out.println(e.getClass());
//            }
//            return 1;
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.cancel(true);
        try {
            future.get();
        } catch (InterruptedException e) {
            System.out.println("ok2");
            System.out.println(e.getClass());

        } catch (ExecutionException e) {
            System.out.println("ok3");
            System.out.println(e.getClass());
            System.out.println(e.getCause());

        }catch (CancellationException e){
            System.out.println("ok4");
            System.out.println(e.getClass()+"-->"+future.isDone());
        }
        System.out.println("isCancelled..."+future.isCancelled());
        System.out.println("isDone..."+future.isDone());
        service.shutdownNow();
    }
}
