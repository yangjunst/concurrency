package com.maomao.learn.concurrcy.base;

/********************************************
 * 文件名称: ThreadGroupDemo4.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/29 14:36
 *********************************************/

/**
 * 输出流out与err的输出顺序不一致。
 */
public class ThreadGroupDemo4 {
    public static void main(String[] args) {
        ThreadGroup group1=new ThreadGroup("g1");
        ThreadGroup group2 = new ThreadGroup(group1, "g2");
        ThreadGroup group3 = new ThreadGroup(group2, "g3");
        System.out.println(group1.getName());
        System.out.println(group2.getName());
        System.out.println(group3.getName());
        System.err.println(group1.getParent().getName());
        System.err.println(group2.getParent().getName());
        System.err.println(group3.getParent().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getClass().getName());
    }
}
