package example;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/6 15:27
 * Program Goal:
 *********************************************/
public class WaitZeroDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            synchronized (WaitZeroDemo.class){
                try {
                    System.out.println("wait before...");
                    WaitZeroDemo.class.wait(0);
                    System.out.println("wait after...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("wait finally...");
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        synchronized (WaitZeroDemo.class){
            WaitZeroDemo.class.notify();
        }
    }
}
