package com.roocon.thread.demo;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/21 13:11
 * Program Goal:
 * һ�ٸ������ȼ��̣߳�һ�������ȼ��߳�
 * ��Ȼ�޷����γ���������
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
