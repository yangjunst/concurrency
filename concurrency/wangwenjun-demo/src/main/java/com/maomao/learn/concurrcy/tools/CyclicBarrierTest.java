package com.maomao.learn.concurrcy.tools;

import javafx.scene.shape.Cylinder;

import javax.rmi.CORBA.Tie;
import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: CyclicBarrierTest.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/23 15:41
 *********************************************/
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        final CyclicBarrier barrier=new CyclicBarrier(2);
        Thread t=new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                System.out.println(barrier.isBroken());
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.getParties());
        barrier.reset();
        System.out.println(barrier.getNumberWaiting());
        System.out.println(barrier.getParties());
    }
}
