package example;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/28 14:46
 * Program Goal:可以先{@link Thread#interrupt()}
 * 那么对于中断线程，则保留中断标记，直到碰到可中断代码，
 * 进行中断操作
 *********************************************/
public class InterruptBeforeWaitingDemo {
    public static void example1() {
        Thread current=Thread.currentThread();
        Thread t1=new Thread(()->{
            try {
                System.out.println("join before...");
                current.join();
                System.out.println("join after...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("before...");
            while (!Thread.currentThread().isInterrupted()){}
            System.out.println("after...");
            System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().isInterrupted());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("sleep--->"+e.getClass());
            }
        });
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }

    public static void example2() {
        Thread thread=new Thread(()->{
            Thread.currentThread().interrupt();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        example2();
    }

}
