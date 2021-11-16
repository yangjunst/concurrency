package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/********************************************
 * 文件名称: AtomicBooleanDemi.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/10 15:45
 *********************************************/
public class AtomicBooleanDemo {

    public static void main(String[] args) {
        AtomicBoolean b=new AtomicBoolean();
        boolean result = b.compareAndSet(false, true);
        System.out.println(result);
        System.out.println(b.get());

    }
}
