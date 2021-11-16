package com.maomao.learn.concurrcy.base;

/********************************************
 * 文件名称: ThreadGroupDemo3.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/29 14:22
 *********************************************/
public class ThreadGroupDemo3 {
    public static void main(String[] args) {

        new Thread(new ThreadGroup("love"), () -> {
            Thread  t=new Thread() {
                @Override
                public void run() {
                    System.out.println(getThreadGroup().getName());
                }
            };
        }, "t").start();

    }
}
