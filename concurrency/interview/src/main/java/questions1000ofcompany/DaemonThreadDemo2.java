package questions1000ofcompany;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 14:10
 * Program Goal:
 * 守护线程不执行finally子句的情况下就会终止其run()方法。
 *********************************************/
public class DaemonThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(10);
                System.out.println("daemon thread done...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("daemon thread finally...");
            }
        });
        t1.setDaemon(true);
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("main thread done...");
    }
}
