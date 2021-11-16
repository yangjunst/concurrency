package com.wangwenjun.demo.chapter17;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/2 15:27
 *********************************************/
public interface Lock {

    void lock() throws InterruptedException;

    void unlock();
}
