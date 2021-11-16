package com.roocon.thread.demo;

import java.util.concurrent.Semaphore;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/25 20:29
 * Program Goal:
 *********************************************/
public class SemaporeExample implements Runnable{
    private static final  Semaphore semaphore=new Semaphore(10);
    private static final   SemaporeExample example=new SemaporeExample();

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(example).start();
        }
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"-->is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            synchronized (this){
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        System.out.println(Thread.currentThread().getName()+"-->is ending");
    }
}
