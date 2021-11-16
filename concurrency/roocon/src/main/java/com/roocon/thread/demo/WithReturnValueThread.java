package com.roocon.thread.demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/21 10:47
 * Program Goal:
 *********************************************/
public class WithReturnValueThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        Random random=new Random();
        TimeUnit.SECONDS.sleep(5);
        return "yangjun"+random.nextInt(1000);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WithReturnValueThread thread=new WithReturnValueThread();
        FutureTask<String> task=new FutureTask(new WithReturnValueThread());
        Thread t=new Thread(task);
        t.start();
        System.out.println("I am doing other things....");
        String result= task.get();
        System.out.println("result--->"+result);


    }
}
