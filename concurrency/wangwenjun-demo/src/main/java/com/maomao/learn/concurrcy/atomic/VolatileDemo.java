package com.maomao.learn.concurrcy.atomic;

/********************************************
 * 文件名称: VolatileDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/10 11:34
 *********************************************/
public class VolatileDemo {
    private static boolean flag = false;

    private static void incr() {
        synchronized (VolatileDemo.class) {
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            int i = 0;
            while (!flag) i++;
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(flag);
    }
}
