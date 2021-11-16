package com.roocon.thread.demo;

import java.util.concurrent.TimeUnit;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/21 18:49
 * Program Goal:
 *********************************************/
public class DeadLockDemo {
    Object object1 = new Object();
    Object object2 = new Object();


    public void a() {
        synchronized (object1) {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + "��ʼ��...");
                System.out.println(Thread.currentThread().getName() + "������...");
                System.out.println("a");
            }
        }
    }

    public void b() {
        synchronized (object2) {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + "��ʼ��...");
                System.out.println(Thread.currentThread().getName() + "������...");
                System.out.println("b");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo d = new DeadLockDemo();
        new Thread(() -> d.a()).start();
        new Thread(() -> d.b()).start();
       /* new Thread(){
            @Override
            public void run() {
                d.a();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                d.b();
            }
        }.start();*/
    }
}
