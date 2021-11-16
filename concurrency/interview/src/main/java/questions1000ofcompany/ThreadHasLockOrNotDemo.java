package questions1000ofcompany;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 15:33
 * Program Goal:
 *********************************************/
public class ThreadHasLockOrNotDemo {
    private static final Object o=new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            try {
                synchronized (o){
                    TimeUnit.SECONDS.sleep(1);
                    boolean b = Thread.holdsLock(o);
                    System.out.println(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();


    }
}
