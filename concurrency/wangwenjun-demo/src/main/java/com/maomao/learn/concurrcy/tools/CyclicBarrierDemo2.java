package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: CyclicBarrierDemo2.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/29 12:45
 *********************************************/
public class CyclicBarrierDemo2 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier=new CyclicBarrier(1);
        int await = barrier.await();
        System.out.println(await);
        await = barrier.await();
        System.out.println(await);
        await = barrier.await();
        System.out.println(await);
    }
}
