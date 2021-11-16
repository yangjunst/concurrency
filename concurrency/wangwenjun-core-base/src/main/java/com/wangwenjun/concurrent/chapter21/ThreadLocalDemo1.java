package com.wangwenjun.concurrent.chapter21;

import java.util.concurrent.ThreadLocalRandom;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/4 10:53
 *********************************************/
public class ThreadLocalDemo1 {
    public static void main(String[] args) {

        ThreadLocal<Integer> locala=ThreadLocal.withInitial(()->ThreadLocalRandom.current().nextInt());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("a-->" + Thread.currentThread().getName() + "-v-->" + locala.get());
            }, String.valueOf(i)).start();
        }
    }
}
