package com.roocon.thread.demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/21 10:12
 * Program Goal:
 *********************************************/
public class InterruptedDemo extends Thread{
    InterruptedDemo(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println(getName()+"--->"+interrupted());
        System.out.println(getName()+"--->"+interrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptedDemo yangjun = new InterruptedDemo("yangjun");
        yangjun.start();
        yangjun.interrupt();

    }
}
