package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/********************************************
 * 文件名称: AtomicReferenceDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/10 16:31
 *********************************************/
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicStampedReference a;
        Simple simple=new Simple("yangjunst",31);
        AtomicReference<Simple> atomic=new AtomicReference<>(simple);
        System.out.println(atomic.compareAndSet(simple,new Simple("yangjun",31)));
        atomic.set(new Simple("maomao",15));
        System.out.println(atomic.get());
    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
