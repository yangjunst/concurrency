package com.maomao.learn.concurrcy.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicIntegerTest implements Runnable
{

    private static AtomicInteger i=new AtomicInteger(0);
    private static Set<Integer> set= Collections.synchronizedSet(new HashSet<>());
    @Override
    public void run() {
        int x = 0;
        while (x < 50) {
            int v=i.getAndIncrement();
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            set.add(v);
            x++;

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AtomicIntegerTest(),"t1");
        Thread t2 = new Thread(new AtomicIntegerTest(),"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(set.size());
    }

}