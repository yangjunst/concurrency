package com.maomao.learn.concurrcy.collection;

import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: StaticBlockThreadDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/31 11:51
 *********************************************/
public class StaticBlockThreadDemo {
    static {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();
        System.out.println("ok");
        try {
            TimeUnit.SECONDS.sleep(20);
            System.out.println("finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("yes");
    }
}
