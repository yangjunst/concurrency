package example;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/3 13:13
 * Program Goal:
 *********************************************/
public class JoinNotifyAllDemo {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"--->start...");
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName()+"---->again...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+"---->finally...");
            }
        });

        Thread t2=new Thread(()->{
            try {
                System.out.println("t2 join before...");

                t1.join();
                System.out.println("t2 start...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("t2 finish...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3=new Thread(()->{
            try {
                System.out.println("t3 join before...");

                t1.join();
                System.out.println("t3 start...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("t3 finish...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
