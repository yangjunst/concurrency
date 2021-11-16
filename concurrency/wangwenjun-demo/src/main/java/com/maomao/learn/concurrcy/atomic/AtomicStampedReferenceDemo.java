package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/********************************************
 * 文件名称: AtomicStampedReferenceDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/16 8:25
 *********************************************/
public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> reference=new AtomicStampedReference<>(100,20);
        reference.set(10,2);
        System.out.println(reference.getReference());
        System.out.println(reference.getStamp());
        int[] arr=new int[Short.MAX_VALUE];
        System.out.println(reference.get(arr));
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

}
