package com.maomao.learn.concurrcy.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/********************************************
 * 文件名称: ConditionDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/28 9:59
 *********************************************/
public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        new Thread(()->{
            try {
                lock.lock();
                condition.await(5, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+"finish await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"T2").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "finish await");
            }finally {
                lock.unlock();
            }
        },"T1").start();
    }
}
