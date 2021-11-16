package com.maomao.learn.concurrcy.base;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: ThreadGroupDemo7.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/30 10:16
 *********************************************/
public class ThreadGroupDemo6 {
    public static void main(String[] args) throws InterruptedException {
        final ThreadGroup group=new ThreadGroup("mygroup");
        final Random random=new Random(41);

        for(int i=0;i<50;i++){
            new Thread(group,()->{
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t"+i).start();
        }

        TimeUnit.SECONDS.sleep(3);
        int i = group.activeCount();
        System.out.println(i);
        System.out.println("==============================");
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(group,"t51").start();
        new Thread(group,"t52").start();
        Thread[] threads=new Thread[50];


        group.enumerate(threads);

        long count = Arrays.stream(threads).filter(t -> t != null).count();
        System.out.println(count);

    }
}
