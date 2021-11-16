package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 *  * 入职时间: 2016/05/16
 *  * 开发时间: 2021/8/21 18:12
 *  * Program Goal:
 *********************************************/
public class ReentrantLockDemo {
    public synchronized void a(){
        System.out.println("a.."+Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void b(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b.."+Thread.currentThread().getName());
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo demo=new ReentrantLockDemo();
        new Thread(()->{
            demo.a();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        demo.b();
    }
}
