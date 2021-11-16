package com.maomao.learn.concurrcy.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: ArrayBlockingQueueDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/31 10:50
 *********************************************/
public class ArrayBlockingQueueDemo {
    static Thread t3 = null;
    static Thread t4 = null;

    public static void t1() {
        Thread t1 = new Thread(() -> {
            queue.add(1);
//            queue.add(null);
//            queue.add(2);
//            try {
//                queue.add(3);
//            }catch (RuntimeException e){
//                System.out.println("full");
//            }
//          ;try {
//                queue.add(4);
//            }catch (RuntimeException e){
//            }
//            System.out.println(queue);
        });
        t1.start();
    }

    public static void t2() {
        Thread t2 = new Thread(() -> {
            Boolean b = null;
            b = queue.offer(1);
            System.out.println(b);
            b = queue.offer(2);
            System.out.println(b);
            b = queue.offer(3);
            System.out.println(b);
            b = queue.offer(3);
            System.out.println(b);
            System.out.println(queue);
        });
        t2.start();
    }

    public static void t3() {
        t3 = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            try {
                System.out.println(queue.offer(3, 3, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                System.out.println("Timeout");
            }
            ;

        });
        t3.start();
    }

    public static void t4() {
        t4 = new Thread(() -> {
            try {
                queue.put(1);
                queue.put(2);
                queue.put(3);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
        });
        t4.start();
    }

    static Thread t5;
    static Thread t6;

    public static void r1() {
        t5 = new Thread(() -> {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                System.out.println(e.getClass());
            }
        });
        t5.start();
    }


    public static void r2() {
        t6 = new Thread(() -> {
            try {
                System.out.println(queue.poll());
                System.out.println(queue.poll());
                System.out.println(queue.poll(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                System.out.println(e.getClass());
                try {
                    System.out.println(queue.poll(1, TimeUnit.SECONDS));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        t6.start();
    }

    final static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);


    public static void main(String[] args) throws InterruptedException {
        queue.put(1);
        System.out.println(queue.remove());
        System.out.println(queue.poll());
    }
}
