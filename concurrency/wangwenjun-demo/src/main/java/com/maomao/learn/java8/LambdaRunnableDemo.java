package com.maomao.learn.java8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/********************************************
 * 文件名称: LambdaRunnableDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/24 12:14
 *********************************************/
public class LambdaRunnableDemo {
    public static void main(String[] args) {
        new Thread(LambdaRunnableDemo::display,"yangjunst").start();
    }

    public static void display(){
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        Lock lock = readWriteLock.readLock();
        lock.newCondition();
        System.out.println(Thread.currentThread().getName()+"-->display is running...");
    }
}
