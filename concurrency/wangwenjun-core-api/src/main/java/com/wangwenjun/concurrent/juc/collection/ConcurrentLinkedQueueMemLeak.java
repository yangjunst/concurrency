package com.wangwenjun.concurrent.juc.collection;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentLinkedQueueMemLeak
{
    public static void main(String[] args) throws InterruptedException
    {
        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        //queue.add(new Object()); //Required for the leak to appear.
        Object object = new Object();

        int loops = 0;

        TimeUnit.SECONDS.sleep(30);

        Stopwatch watch = Stopwatch.createStarted();
        while (true)
        {
            if (loops % 10000 == 0 && loops != 0)
            {
                long elapsedMs = watch.stop().elapsed(TimeUnit.MILLISECONDS);
                System.out.printf("loops=%d duration=%d MS%n", loops, elapsedMs);
                watch.reset().start();
            }
            queue.add(object);
            queue.remove(object);
//            queue.poll();
            ++loops;
        }
    }
} 