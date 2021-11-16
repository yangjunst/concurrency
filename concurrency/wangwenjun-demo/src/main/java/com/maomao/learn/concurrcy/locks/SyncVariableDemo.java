package com.maomao.learn.concurrcy.locks;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/27 17:50
 *********************************************/
public class SyncVariableDemo {
    static int[] arrs = IntStream.rangeClosed(1, 10).toArray();

    public static void main(int[] arr) {
        synchronized (arr){
            for (int i = 0; i < arr.length; i++) {
                arr[i] *= 5;
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(()->main(arrs));
        service.execute(()->main(arrs));
        service.execute(()->main(arrs));
        Thread.sleep(1000);
        System.out.println(Arrays.toString(arrs));
        service.shutdown();
    }
}
