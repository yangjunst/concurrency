package com.maomao.learn.concurrcy.base;

/********************************************
 * 文件名称: ThreadGroupDemo2.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/29 14:00
 *********************************************/
public class ThreadGroupDemo2 {
    public static void main(String[] args) {
        Thread t=new Thread(new ThreadGroup("group"),()->{
            ThreadGroup threadGroup = new Thread(new ThreadGroup("group1"),null,"t2").getThreadGroup();
            System.out.println(threadGroup.getName());
            System.out.println(threadGroup.getParent().getName());

        },"t1");
        t.start();
        System.out.println(t);
    }
}
