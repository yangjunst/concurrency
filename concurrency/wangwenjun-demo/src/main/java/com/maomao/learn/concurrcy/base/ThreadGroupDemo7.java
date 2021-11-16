package com.maomao.learn.concurrcy.base;

/********************************************
 * 文件名称: ThreadGroupDemo7.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/5/5 12:53
 *********************************************/
public class ThreadGroupDemo7 {
    public static void main(String[] args) {
        ThreadGroup group=new ThreadGroup("g1");
        group.setMaxPriority(5);
        Thread t=new Thread(group,"");
        t.setPriority(10);
        System.out.println(group.getMaxPriority());
        System.out.println(t.getPriority());
        Thread.currentThread().getThreadGroup().getParent().list();
        System.out.println(group.parentOf(group));

    }
}
