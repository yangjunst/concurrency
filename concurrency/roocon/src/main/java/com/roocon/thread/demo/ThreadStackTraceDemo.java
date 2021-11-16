package com.roocon.thread.demo;

import java.util.Map;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/20 19:34
 * Program Goal:
 *********************************************/
public class ThreadStackTraceDemo {
    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for(Map.Entry<Thread,StackTraceElement[]> entry:allStackTraces.entrySet()){
            System.out.println(entry.getKey().getName());
            for(StackTraceElement element:entry.getValue()){
                System.out.println("\t"+element);
            }
        }

    }
}
