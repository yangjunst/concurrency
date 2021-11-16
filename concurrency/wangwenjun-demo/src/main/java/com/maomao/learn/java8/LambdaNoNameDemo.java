package com.maomao.learn.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/********************************************
 * 文件名称: LambdaNoNameDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/16 14:22
 *********************************************/
public class LambdaNoNameDemo {
    interface Inter {
        int display(int a, int b);

        void show();
    }

    static class A implements Inter {

        @Override
        public int display(int a, int b) {
            return a + b;
        }

        @Override
        public void show() {
            System.out.println("A show...");
        }
    }

    public static void main(String[] args) {
        Iterator<Comparator<Integer>> iterable = Arrays.asList(new A(), new A(), new A()).stream().map(a -> (Comparator<Integer>) a::display).iterator();
        System.out.println(iterable.next().compare(1, 2));
        System.out.println(iterable.next().compare(2, 3));
        System.out.println(iterable.next().compare(3, 4));
    }
}
