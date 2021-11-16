package com.wangwenjun.juc.collections.blocking.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/7 15:52
 * Program Goal:
 *********************************************/
public class UnboundedQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue=new PriorityBlockingQueue<>();
        long n=Long.valueOf(Integer.MAX_VALUE)/10;
        for(long i=0;i<n;i++){
            queue.add(1);
        }
        System.out.println("ok");
        System.out.println(queue.size());
        System.out.println(queue.remainingCapacity());
    }
}
