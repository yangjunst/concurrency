package example;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/6 10:40
 * Program Goal:
 *********************************************/
public class InterruptedNoteExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
           while (!Thread.currentThread().isInterrupted()){
               try {
                   TimeUnit.SECONDS.sleep(5);
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   Thread.currentThread().interrupt();
               }
           }
            if(Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getClass()+"-->again...");
                }
            }
        });
        t1.start();
        TimeUnit.NANOSECONDS.sleep(1);
        t1.interrupt();
    }
}
