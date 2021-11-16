package com.wangwenjun.concurrent.juc.utils;

import com.google.common.util.concurrent.RateLimiter;

import static java.lang.Thread.currentThread;

public class RateLimiterExample1
{

    private final static RateLimiter rateLimiter = RateLimiter.create(0.5);

    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            new Thread(RateLimiterExample1::testRateLimiter).start();
        }
    }

    private static void testRateLimiter()
    {
        double elapsedSecond = rateLimiter.acquire();
        System.out.println(currentThread() + ": elapsed seconds: " + elapsedSecond);
    }
}
