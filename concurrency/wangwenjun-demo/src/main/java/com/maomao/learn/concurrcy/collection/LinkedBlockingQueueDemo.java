package com.maomao.learn.concurrcy.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/********************************************
 * 文件名称: LinkedBlockingQueueDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/31 14:16
 *********************************************/
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue=new LinkedBlockingQueue(1);
        queue.put(1);
        System.out.println(queue);
        queue.put(2);
        System.out.println(queue);

    }
}
