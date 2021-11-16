package com.maomao.learn.language;

import java.util.Arrays;
import java.util.stream.IntStream;

/********************************************
 * 文件名称: SystemCopyOfDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/30 8:43
 *********************************************/
public class SystemArrayCopyDemo {
    public static void main(String[] args) {
        int[] arr1={1,2,3,4,5,6,7};
        int[] arr2=new int[10];
        System.arraycopy(arr1,1,arr2,2,6);
        Arrays.stream(arr2).forEach(System.out::println);
    }
}
