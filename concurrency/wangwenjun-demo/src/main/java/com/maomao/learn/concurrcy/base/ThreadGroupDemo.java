package com.maomao.learn.concurrcy.base;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: ThreadGroupDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/28 4:33
 *********************************************/
public class ThreadGroupDemo {
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<50;i++){
            new Thread("i->"+i){
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        TimeUnit.SECONDS.sleep(3);
        Thread[] threads=new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threads,true);
        Arrays.stream(threads).forEach(System.out::println);
    }
}
