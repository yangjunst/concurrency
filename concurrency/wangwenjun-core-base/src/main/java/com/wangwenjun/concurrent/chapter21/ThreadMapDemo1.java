package com.wangwenjun.concurrent.chapter21;

import java.util.concurrent.ConcurrentHashMap;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/4 10:32
 *********************************************/
public class ThreadMapDemo1 {
    public static void main(String[] args) {
        ConcurrentHashMap<Thread,Integer> map=new ConcurrentHashMap<>();
        Thread t1=new Thread();
        Thread t2=new Thread();
        map.put(t1,1);
        map.put(t2,2);
        t1.start();
        t2.start();
        System.out.println(map);
        map.keySet().forEach(e-> System.out.println(e.getState()));


    }
}
