package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample3
{
    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatch latch = new CountDownLatch(0);
        latch.await(10, TimeUnit.SECONDS);
    }
}
