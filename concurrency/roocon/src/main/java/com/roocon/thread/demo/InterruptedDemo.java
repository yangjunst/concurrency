package com.roocon.thread.demo;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/21 10:12
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
