package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/25 14:45
 * Program Goal:
 *********************************************/
public class JoinExample implements Runnable {
    public static void main(String[] args) {
        Thread joinThread=new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        joinThread.start();
        JoinExample joinExample = new JoinExample();
        joinExample.a(joinThread);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok..........");
        joinExample.b(joinThread);



    }
    public void a(Thread joinThread) {
        System.out.println(Thread.currentThread().getName()+" a()ִ����...");
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" a()ִ�н�����...");
    }

    public void b(Thread joinThread) {
        System.out.println(Thread.currentThread().getName()+" b()ִ����...");
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" b()ִ�н�����...");

    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->ִ����");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->ִ�н�����");

    }
}
