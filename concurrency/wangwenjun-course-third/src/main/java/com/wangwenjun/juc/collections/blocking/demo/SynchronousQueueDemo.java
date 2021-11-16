package com.wangwenjun.juc.collections.blocking.demo;

import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 8:21
 * Program Goal:
 *********************************************/
public class SynchronousQueueDemo {
    public static void test() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    queue.put("hello" + (++i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue queue=new SynchronousQueue();
//        queue.add("1");//IllegalStateException: Queue full
        queue.offer("1");
        queue.remove();//NoSuchElementException
        queue.poll();
    }
}
