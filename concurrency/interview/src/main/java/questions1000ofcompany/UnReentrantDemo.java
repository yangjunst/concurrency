package questions1000ofcompany;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 14:19
 * Program Goal:
 *********************************************/
public class UnReentrantDemo {
    private static final Lock lock = new ReentrantLock();
    public static void outer() {
        lock.lock();
        System.out.println("outer before inner...");
        inner();
        System.out.println("outer after inner...");
        lock.unlock();
    }

    public static void inner() {
        lock.lock();
        System.out.println("inner");
        lock.unlock();
    }

    public static void main(String[] args) {
        outer();
    }
}