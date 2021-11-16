package com.wangwenjun.concurrent.juc.collection;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample
{
    public static void main(String[] args)
    {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        assert queue.remainingCapacity() == 10;

    }
}