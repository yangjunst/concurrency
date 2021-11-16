package com.wangwenjun.concurrent.juc.collection;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueExample
{
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();


//        assert queue.remainingCapacity() == 0x7fffffff;
        queue.put(1111);
        System.out.println(queue);
        System.out.println(queue.remainingCapacity()==Integer.MAX_VALUE);
//        assert queue.size() == 4;
//
//        assert queue.poll() == 1;
//        assert queue.poll() == 3;
//        assert queue.poll() == 10;
//        assert queue.poll() == 14;

    }
}
