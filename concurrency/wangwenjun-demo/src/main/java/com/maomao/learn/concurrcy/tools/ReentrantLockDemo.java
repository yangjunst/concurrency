package com.maomao.learn.concurrcy.tools;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/********************************************
 * 文件名称: ReentrantLock.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/22 9:23
 *********************************************/
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        lock.unlock();
        StampedLock stampedLock=new StampedLock() ;
        stampedLock.writeLock();
    }
}
