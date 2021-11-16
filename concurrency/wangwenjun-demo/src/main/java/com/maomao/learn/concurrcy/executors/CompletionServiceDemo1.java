package com.maomao.learn.concurrcy.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/24 11:14
 *********************************************/
public class CompletionServiceDemo1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService=new ExecutorCompletionService<>(executor);
        List<Callable<Integer>> callables= Arrays.asList(
                ()->{
                    TimeUnit.MILLISECONDS.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"--->"+3000);
                    return 3000;
                },
                ()->{
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"--->"+1000);
                    return 1000;
                },
                ()->{
                    TimeUnit.MILLISECONDS.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"--->"+2000);
                    return 2000;
                }
        );
        callables.forEach(completionService::submit);
        for(int i=0;i<callables.size();i++){
            try {
                Future<Integer> future = completionService.take();
                System.out.println("-->"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
