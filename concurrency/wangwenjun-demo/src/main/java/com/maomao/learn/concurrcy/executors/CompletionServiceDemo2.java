package com.maomao.learn.concurrcy.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/24 16:03
 *********************************************/
public class CompletionServiceDemo2 {
    public static void main(String[] args)  {
        ExecutorService service= Executors.newCachedThreadPool();
        CompletionService<AtomicInteger> completionService= new ExecutorCompletionService<>(service);
        final AtomicInteger atomicInteger=new AtomicInteger();
        List<Runnable> list= Arrays.asList(
                ()->{
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                        atomicInteger.set(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"--->"+3000);
                },
                ()->{
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        atomicInteger.set(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"--->"+1000);
                },
                ()->{
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                        atomicInteger.set(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"--->"+2000);
                }
        );
        list.forEach(r-> completionService.submit(r,atomicInteger));
        for(int i=0;i<list.size();i++){
            try {
                Future<AtomicInteger> future = completionService.poll(1222,TimeUnit.MILLISECONDS);
                System.out.println("result--->"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}
