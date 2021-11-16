package demo;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/10 9:29
 * Program Goal:
 *********************************************/
public class ReentrantReadWriteLockDemo {
    private static final ReadWriteLock rwl=new ReentrantReadWriteLock();

    public static void main(String[] args) {
        rwl.readLock();
        rwl.writeLock();
    }
}
