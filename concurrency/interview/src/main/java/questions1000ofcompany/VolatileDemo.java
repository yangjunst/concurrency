package questions1000ofcompany;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/27 16:23
 * Program Goal:
 *********************************************/
public class VolatileDemo {
    private static boolean f = false;
    private static final Lock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!f) {
                lock.lock();
                lock.unlock();
            }
            System.out.println("ok");
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("update f....start....");
        f = true;
        System.out.println("update f....end....");
    }
}
