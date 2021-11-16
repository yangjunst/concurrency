package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/9 21:31
 * Program Goal:
 *********************************************/
public class ReentrantLockApiDemo {
    private static final ReentrantLock lock=new ReentrantLock();
    private static final Condition c=lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<4;i++){
            final int k=i;
            new Thread(){
                @Override
                public void run() {
                    lock.lock();
                    try {
                        boolean b = lock.hasWaiters(c);
                        System.out.println(b+"-->"+lock.getWaitQueueLength(c));
                        TimeUnit.MILLISECONDS.sleep(k*1500);
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }.start();
        }

    }

}
