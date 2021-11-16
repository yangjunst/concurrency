package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

/********************************************
 * 文件名称: AtomicIntegerArrayDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/15 14:57
 *********************************************/
public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(arr);
        int andIncrement = atomicIntegerArray.getAndIncrement(1);

        System.out.println(andIncrement);
        System.out.println(atomicIntegerArray.get(1));

    }
}
