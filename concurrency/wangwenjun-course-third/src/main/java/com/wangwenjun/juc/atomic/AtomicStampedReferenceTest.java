package com.wangwenjun.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/9
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    boolean success = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(success);
                    System.out.println("11111->"+atomicRef.getReference()+"-->"+atomicRef.getStamp());

                    success = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(success);
                    System.out.println("22222->"+atomicRef.getReference()+"-->"+atomicRef.getStamp());


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    int stamp = atomicRef.getStamp();
                    System.out.println("Before sleep:stamp=" + stamp);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("333333->"+atomicRef.getReference()+"-->"+atomicRef.getStamp());


                    boolean success = atomicRef.compareAndSet(100, 101, stamp, stamp + 1);
                    System.out.println("444444->"+atomicRef.getReference()+"-->"+atomicRef.getStamp());

                    System.out.println(success);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        t1.join();
        t2.join();
    }
}
