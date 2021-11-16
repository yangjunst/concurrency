package example;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/28 14:39
 * Program Goal:
 * 先notify 而后在wait，那么wait的线程是不会被唤醒的
 *********************************************/
public class NotifyBeforeWaitDemo {
    private static final Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("waiting before");
                    o.wait();
                    System.out.println("waiting after");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"--->finish...");
                }
            }
        },"t1");
        Thread t2 = new Thread(()->{
            synchronized (o){
                System.out.println("before notify...");
                o.notify();
                System.out.println("after notify...");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--->finish...");
            }
        },"t2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(1500);
        t1.start();
    }
}
