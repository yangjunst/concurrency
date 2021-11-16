package com.wangwenjun.juc.collections.blocking.demo;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/7 15:08
 * Program Goal: All BlockingQueue meet null element
 * will be throw NullPointerException
 *********************************************/
public class NullPointerOfBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue = new LinkedBlockingQueue<>();
        queue = new LinkedTransferQueue<>();
        queue = new PriorityBlockingQueue<>();
        queue = new DelayQueue();
        queue = new SynchronousQueue<>();
        queue.put(null);
    }
}
