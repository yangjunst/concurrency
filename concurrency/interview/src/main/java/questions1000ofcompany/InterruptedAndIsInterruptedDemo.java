package questions1000ofcompany;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 15:24
 * Program Goal:
 *********************************************/
public class InterruptedAndIsInterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            try {
                while (!Thread.interrupted()){
                    System.out.println("yes...");
                }
                System.out.println("ok"+Thread.currentThread().isInterrupted());
            } catch (Exception e) {
                System.out.println(e.getClass());

            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();

    }
}
