package com.roocon.thread.demo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/23 10:02
 * Program Goal:
 * AtomicIntegerArray对int[]数组元素的一个拷贝，
 * 修改AtomicIntegerArray中元素的值，
 * 不影响int[]里面元素的值
 *********************************************/
public class AtomicIntegerArrayDemo {
    private static final int[] arr={1,3,4,6,7,8,9};
    private static final AtomicIntegerArray a=new AtomicIntegerArray(arr);
    public static void getAndAdd(int index,int n){
        a.getAndAdd(index,n);
    }

    public static void main(String[] args) {
        getAndAdd(1,10);
        System.out.println(Arrays.toString(arr));
        System.out.println(a.toString());
    }
}
