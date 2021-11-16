package questions1000ofcompany;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 13:58
 * Program Goal:
 *********************************************/
public class NotifyVSNotifyAllDemo {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                    TimeUnit.SECONDS.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK) {
                /**
                 * t1--->WAITING
                 * t2--->TIMED_WAITING
                 * t3--->WAITING
                 * t4--->TERMINATED
                 */
//                LOCK.notify();
                /**
                 * t1--->BLOCKED
                 * t2--->TIMED_WAITING
                 * t3--->BLOCKED
                 * t4--->TERMINATED
                 */
                LOCK.notifyAll();
                try {
                    TimeUnit.SECONDS.sleep(12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1--->"+t1.getState());
        System.out.println("t2--->"+t2.getState());
        System.out.println("t3--->"+t3.getState());
        System.out.println("t4--->"+t4.getState());
    }
}
