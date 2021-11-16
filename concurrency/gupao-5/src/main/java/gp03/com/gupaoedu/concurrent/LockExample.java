package gp03.com.gupaoedu.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class LockExample {

    static Lock lock=new ReentrantLock();  //取决于这个对象实例的范围(锁的范围)

    private static int count=0;
    public static void inc(){
        //排号系统
        lock.lock();// 抢占锁 //如果没有抢占到锁，会阻塞
        try {
            Thread.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();//
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->LockExample.inc(),"t-"+i).start();
        }
        Thread.sleep(3000);
        System.out.println("result："+count);
    }


}
