package com.wangwenjun.concurrent.chapter21;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/4 13:06
 *********************************************/
public class ThreadLocalDemo2 {
    public static void main(Supplier<Integer> supplier) {
        Integer integer = supplier.get();
        System.out.println(integer);
    }
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                main(ThreadLocalRandom.current()::nextInt);
            }).start();
        }
    }
}
