package com.roocon.thread.demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/21 13:11
 * Program Goal:
 * 一百个高优先级线程，一个低优先级线程
 * 依然无法掩饰出饥饿问题
 *********************************************/
public class HungryThreadDemo extends Thread {
    @Override
    public void run() {
        while (true) {
            if ("Y".equals(getName())) {
                System.out.println(getName());
            } else {
                System.out.println("   ");
            }
        }
    }

    public static void main(String[] args) {
        HungryThreadDemo demo1 = new HungryThreadDemo();

        demo1.setName("Y");
        demo1.setPriority(Thread.MIN_PRIORITY);

        demo1.start();
        for (int i = 0; i < 100; i++) {
            HungryThreadDemo demo = new HungryThreadDemo();
            demo.setPriority(Thread.MAX_PRIORITY);
            demo.start();
        }
    }
}
