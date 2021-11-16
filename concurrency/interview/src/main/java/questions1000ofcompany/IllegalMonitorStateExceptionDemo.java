package questions1000ofcompany;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/28 9:41
 * Program Goal:
 *********************************************/
public class IllegalMonitorStateExceptionDemo {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    /**
     * java.lang.IllegalMonitorStateException
     */
    public static void waitTest() {
        try {
            IllegalMonitorStateExceptionDemo.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * java.lang.IllegalMonitorStateException
     */
    public static void notifyTest() {
        IllegalMonitorStateExceptionDemo.class.notify();

    }

    /**
     * java.lang.IllegalMonitorStateException
     */
    public static void awaitTest() {
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * java.lang.IllegalMonitorStateException
     */
    public static void signalTest() {
        condition.signal();
    }

    public static void main(String[] args) {
//        waitTest();
//        notifyTest();
//        awaitTest();
        signalTest();
    }
}
