package com.wangwenjun.juc.collections.blocking.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/7 15:21
 * Program Goal:
 *********************************************/
public class SingleThreadScheduleExecutorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(()->{
            System.out.println("yes");
        },1, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

        scheduledThreadPool.schedule(()->{
            System.out.println("ok");
        }  ,3,TimeUnit.SECONDS);
        scheduledThreadPool.shutdown();
    }
}
