package com.wangwenjun.concurrent.juc.utils;

import com.google.common.util.concurrent.RateLimiter;

import static java.lang.Thread.currentThread;

public class RateLimiterExample2
{

    private final static RateLimiter rateLimiter = RateLimiter.create(2.0d);

    public static void main(String[] args)
    {
        System.out.println(rateLimiter.acquire(4));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(2));

    }
}
