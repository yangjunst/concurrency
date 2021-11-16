package questions1000ofcompany;


import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/27 15:45
 * Program Goal:
 *********************************************/
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "---->start...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "---->finish...");

        }, "t1");

        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--->un park...start");
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "--->un park...finish");
        }, "t2");
        t2.start();
    }
}
