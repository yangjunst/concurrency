package com.maomao.learn.concurrcy.tools;

import java.sql.Time;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: ExchangerDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/29 13:43
 *********************************************/
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        final Exchanger<String> exchanger=new Exchanger<>();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"--->"+exchanger.exchange(Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                System.out.println("exchange exception...");
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"--->"+exchanger.exchange(Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                System.out.println("exchange exception...");
            }
        },"t2");
        t2.start();
        Thread t3 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"--->"+exchanger.exchange(Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                System.out.println("exchange exception...");
            }
        },"t3");
        t3.start();
        Thread t4 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName()+"--->"+exchanger.exchange(Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                System.out.println("exchange exception...");
            }
        },"t4");
        t4.start();
    }
}
