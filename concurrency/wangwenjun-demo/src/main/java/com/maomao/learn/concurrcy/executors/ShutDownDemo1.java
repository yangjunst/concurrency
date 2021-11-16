package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/16 13:53
 *********************************************/
public class ShutDownDemo1 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        service.shutdown();
        service.shutdown();
        service.shutdown();
        service.shutdown();
    }
}
