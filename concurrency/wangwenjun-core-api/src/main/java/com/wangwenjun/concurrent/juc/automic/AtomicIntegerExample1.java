package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample1
{
    public static void main(String[] args)
    {
        AtomicInteger ai = new AtomicInteger(10);
        assert ai.incrementAndGet() == 11;
    }
}
