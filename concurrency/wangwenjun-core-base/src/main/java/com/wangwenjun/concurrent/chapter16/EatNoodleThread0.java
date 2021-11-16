package com.wangwenjun.concurrent.chapter16;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/2 13:19
 *********************************************/
public class EatNoodleThread0 extends Thread {
    private final String name;

    private final Tableware left;
    private final Tableware right;

    public EatNoodleThread0(String name, Tableware left, Tableware right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (left) {
            System.out.println(name + " take up " + left + "(left)");
            synchronized (right) {
                System.out.println(name + " take up " + right + "(right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + right + "(right)");
            }
            System.out.println(name + " put down " + left + "(left)");

        }
        System.out.println("====================");
    }
}
