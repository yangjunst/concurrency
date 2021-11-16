package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/11 10:04
 *********************************************/
public class ExecutorServiceDemo1 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        service.shutdownNow();
        service.shutdownNow();
        service.shutdownNow();
        service.shutdownNow();
    }
}
