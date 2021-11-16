package com.wangwenjun.juc.collections.blocking.demo;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 10:04
 * Program Goal:
 *********************************************/
public class LinkedTransferQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue=new SynchronousQueue<>(false);
        for(int i=0;i<10;i++){
            int j=i;
            new Thread(()->{
                try {
                    queue.put(j+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
        TimeUnit.SECONDS.sleep(1);
        while (true){
            System.out.println(queue.take());
        }
//        testLinkedTransferQueue();
    }
    public static void testLinkedTransferQueue() throws InterruptedException {
        LinkedTransferQueue<String> queue=new LinkedTransferQueue<>();
        for(int i=0;i<10;i++){
            int j=i;
            new Thread(()->{
                try {
                    queue.transfer(j+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
        TimeUnit.SECONDS.sleep(1);
        while (true){
            System.out.println(queue.take());
        }


    }
}
