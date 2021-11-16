package com.maomao.learn.concurrcy.collection;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/********************************************
 * 文件名称: ArrayBlockingQueueExample.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/31 12:29
 *********************************************/
public class ArrayBlockingQueueExample {
    static void produceAndConsumer(){
        IntStream.rangeClosed(0,10).boxed().map(i->new Thread("p-thread-"+i){
            @Override
            public void run() {
                while (true){
                    String data=String.valueOf(System.currentTimeMillis());
                    try {
                        queue.put(data);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted produce");
                        break;
                    }
                }
            }
        }).forEach(Thread::start);

        IntStream.rangeClosed(0,10).boxed().map(i->new Thread("c-thread-"+i){
            @Override
            public void run() {
                while (true){
                    try {
                        String data=queue.take();
                    } catch (InterruptedException e) {
                        System.out.println("interrupted consumer");
                    }
                }
            }
        }).forEach(Thread::start);
    }
    static final BlockingQueue<String> queue=new ArrayBlockingQueue<>(10);
    public static void main(String[] args) {
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");
        queue.add("f");
        queue.add("g");
        ArrayList<String> list = new ArrayList<>();
        int i = queue.drainTo(list);
        System.out.println(i);
        System.out.println(queue);
        System.out.println(list);
        System.out.println(queue.remainingCapacity());


    }
}
