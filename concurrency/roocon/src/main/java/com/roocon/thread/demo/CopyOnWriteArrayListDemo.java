package com.roocon.thread.demo;

import java.util.concurrent.CopyOnWriteArrayList;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/27 13:23
 * Program Goal:
 *********************************************/
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList list=new CopyOnWriteArrayList();
        list.add(1);
        list.get(1);
    }
}
