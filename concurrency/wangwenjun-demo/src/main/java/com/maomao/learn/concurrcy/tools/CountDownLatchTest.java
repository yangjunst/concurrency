package com.maomao.learn.concurrcy.tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 文件名称: CountDownLatchTest.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/23 12:37
 *********************************************/
public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(5);
        final CountDownLatch latch=new CountDownLatch(2);
        for(int i=0;i<2;i++){
            service.submit(()->{
                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                if(latch.getCount()==0){
                    System.out.println(Thread.currentThread().getName()+"--->finished....");
                }else{
                    System.out.println(Thread.currentThread().getName()+"--->ok....");
                }
            });
        }
        service.shutdown();
    }
}
