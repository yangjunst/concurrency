package com.wangwenjun.concurrent.juc.collection;

import java.util.Deque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueueExample
{


    private static void getWaitingConsumerCount() throws InterruptedException
    {
        Deque d;
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        for (int i = 0; i < 3; i++)
        {
            new Thread(() ->
            {
                try
                {
                    System.out.println(queue.take());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " consume data over.");
            }).start();
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(queue.hasWaitingConsumer());
        System.out.println(queue.getWaitingConsumerCount());
        queue.offer("test");
        System.out.println(queue.hasWaitingConsumer());
        System.out.println(queue.getWaitingConsumerCount());
//        assert queue.hasWaitingConsumer();
//        assert queue.getWaitingConsumerCount() == 3;
//        queue.offer("test");
//        assert queue.hasWaitingConsumer();
//        assert queue.getWaitingConsumerCount() == 2;
    }

    private static void tryTransferWithTimeout() throws InterruptedException
    {
        final LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.add("hello");
        queue.offer("world");
        new Thread(() ->
        {
            try
            {
               queue.tryTransfer("Alex", 3, TimeUnit.SECONDS);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("current thread exit.");
        }).start();
        System.out.println(queue.take());
        System.out.println(queue.take());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(queue.take());
        System.out.println("ok");
//        assert queue.take().equals("hello");
//        assert queue.take().equals("world");
//        assert queue.take().equals("Alex");
    }

    private static void tryTransfer() throws InterruptedException
    {
        final LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.add("hello");
        queue.offer("world");
        new Thread(() ->
        {
            System.out.println(queue.tryTransfer("Alex"));
            System.out.println("current thread exit.");
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(queue.size());
        System.out.println(queue.remainingCapacity()==Integer.MAX_VALUE);
    }

    private static void transfer() throws InterruptedException
    {
        final LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.add("hello");
        queue.offer("world");
        queue.put("Java");


       new Thread(() ->
        {
            try
            {
                queue.transfer("Alex");
                System.out.println("Alex is used");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            try {
                System.out.println("current thread exit."+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(queue.take());
        queue.put("Scala");
        System.out.println(queue.poll());
        System.out.println(queue.take());

        System.out.println(queue.take());
    }

    public static void main(String[] args) throws InterruptedException
    {
        getWaitingConsumerCount();
    }
}