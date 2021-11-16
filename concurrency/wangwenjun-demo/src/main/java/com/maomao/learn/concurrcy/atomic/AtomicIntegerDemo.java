package com.maomao.learn.concurrcy.atomic;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/********************************************
 * 文件名称: demo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/8 14:08
 *********************************************/
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        i.accumulateAndGet(1, Integer::max);
    }

}
