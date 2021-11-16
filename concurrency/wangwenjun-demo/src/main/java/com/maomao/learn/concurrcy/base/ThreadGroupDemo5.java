package com.maomao.learn.concurrcy.base;

import java.util.Arrays;

/********************************************
 * 文件名称: ThreadGroupDemo5.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/30 8:16
 *********************************************/

/**
 * ThreadGroup[] groups
 * 复制后的元素可能有的为空。
 * 尤其是在非递归复制的情况下
 * 复制数组的元素个数怎样确定？
 */
public class ThreadGroupDemo5 {

    public static void main(String[] args) {
        ThreadGroup group1 = new ThreadGroup("g1");
        ThreadGroup group2 = new ThreadGroup(group1, "g2");
        ThreadGroup group3 = new ThreadGroup(group2, "g3");
        ThreadGroup[] groups=new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()+1];
        int enumerate = Thread.currentThread().getThreadGroup().enumerate(groups, true);
        Arrays.stream(groups).forEach(System.out::println);
        System.out.println(enumerate);


    }

}
