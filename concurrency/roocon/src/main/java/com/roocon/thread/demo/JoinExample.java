package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/25 14:45
 * Program Goal:
 *********************************************/
public class JoinExample implements Runnable {
    public static void main(String[] args) {
        Thread joinThread=new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        joinThread.start();
        JoinExample joinExample = new JoinExample();
        joinExample.a(joinThread);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok..........");
        joinExample.b(joinThread);



    }
    public void a(Thread joinThread) {
        System.out.println(Thread.currentThread().getName()+" a()执行了...");
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" a()执行结束了...");
    }

    public void b(Thread joinThread) {
        System.out.println(Thread.currentThread().getName()+" b()执行了...");
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" b()执行结束了...");

    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->执行了");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->执行结束了");

    }
}
