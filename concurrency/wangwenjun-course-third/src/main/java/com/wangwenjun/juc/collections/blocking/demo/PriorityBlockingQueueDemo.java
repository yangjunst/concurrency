package com.wangwenjun.juc.collections.blocking.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/7 18:47
 * Program Goal:
 *********************************************/
public class PriorityBlockingQueueDemo {
    private static class Example implements Comparable<Example>{

        private String name;
        private Integer age;

        public Example(String name, Integer age) {
            this.name = name;
            this.age = age;
        }


        @Override
        public String toString() {
            return "Example{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Example o) {
            return this.age>o.age?1:-1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Example> queue=new PriorityBlockingQueue<>();
        queue.iterator().next();
        queue.add(new Example("yangjun",31));
        queue.add(new Example("yangjunst",30));
        queue.add(new Example("maomao",13));
        while (true){
            System.out.println(queue.take());
        }


    }
}
