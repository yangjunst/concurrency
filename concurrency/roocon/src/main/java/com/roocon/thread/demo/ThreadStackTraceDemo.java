package com.roocon.thread.demo;

import java.util.Map;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/20 19:34
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
