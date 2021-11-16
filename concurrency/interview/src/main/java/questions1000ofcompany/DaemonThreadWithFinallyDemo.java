package questions1000ofcompany;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 11:15
 * Program Goal:
 *********************************************/
public class DaemonThreadWithFinallyDemo {
    public static void main(String[] args) {
        Thread daemonThread=new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("daemon finally...");
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();

    }
}
