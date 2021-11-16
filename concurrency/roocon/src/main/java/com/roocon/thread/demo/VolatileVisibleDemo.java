package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/23 8:45
 * Program Goal:
 *********************************************/
public class VolatileVisibleDemo {
    private  boolean flag = false;
    private  AtomicBoolean atomicBoolean=new AtomicBoolean(false);

    public  void m() {
        while (!atomicBoolean.get()) {
        }
        System.out.println("ok");
    }

    public  void setFlag(boolean flag) {
        atomicBoolean.set(flag);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileVisibleDemo demo=new VolatileVisibleDemo();
        new Thread(() -> demo.m()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> demo.setFlag(true)).start();
    }
}
