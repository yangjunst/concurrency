package com.wangwenjun.demo.chapter19;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 10:22
 *********************************************/
public interface Future<T> {

    void done(T result);

    T get();
}
