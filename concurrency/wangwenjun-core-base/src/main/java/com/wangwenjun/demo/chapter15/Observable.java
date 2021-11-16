package com.wangwenjun.demo.chapter15;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/1 14:14
 *********************************************/
public interface Observable {

    enum Cycle{
        STARTED,RUNNING,DONE,ERROR;
    }

    Cycle getCycle();

    void start();

    void interrupt();

}
