package com.roocon.thread.demo;

import java.util.concurrent.CountDownLatch;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/25 19:34
 * Program Goal:
 *********************************************/
public class CountDownExample {
    private static final CountDownLatch latch=new CountDownLatch(3);

    private static final void a(){
        try {
            System.out.println("a() begin...");
            latch.await();
            System.out.println("a() over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final void b(){
        try {
            System.out.println("b() begin...");
            latch.await();
            latch.await();
            latch.await();
            latch.await();
            latch.await();
            System.out.println("b()  over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Thread t1=new Thread(()->{a();});
        Thread t2=new Thread(()->{b();});
//        Thread t3=new Thread(()->{b();});
//        t1.start();
        t2.start();
//        t3.start();
        latch.countDown();
        latch.countDown();
        latch.countDown();
        latch.countDown();
        System.out.println("main() over..."+latch.getCount());
    }
}
