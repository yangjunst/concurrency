package com.wangwenjun.concurrent.juc.collection;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeLinkedListConcurrentTest
{

    public static void main(String[] args) throws InterruptedException
    {
        for (int iteration = 0; iteration < 100; iteration++)
        {
            LockFreeLinkedList<Integer> list = new LockFreeLinkedList<>();
            final ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
            final AtomicInteger factory = new AtomicInteger();
            final AtomicInteger deleteCount = new AtomicInteger();
            final CountDownLatch latch = new CountDownLatch(10);
            final int MAX_CAPACITY = 1_000_000;
            for (int i = 0; i < 10; i++)
            {
                new Thread(() ->
                {
                    while (true)
                    {
                        int data = factory.getAndIncrement();
                        if (data < MAX_CAPACITY)
                        {
                            list.add(data);
                            if (data % 2 == 0)
                            {
                                list.removeFirst();
                                deleteCount.incrementAndGet();
                            }
                        } else
                        {
                            break;
                        }
                    }
                    latch.countDown();
                }).start();
            }
            latch.await();

            assert list.count() == (MAX_CAPACITY - deleteCount.get());

            while (!list.isEmpty())
            {
                set.add(list.removeFirst());
            }

            assert set.size() == (MAX_CAPACITY - deleteCount.get());
            System.out.printf("The iteration %d passed concurrent testing %n", iteration + 1);
        }
    }
}
