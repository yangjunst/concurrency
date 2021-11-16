package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample5
{

    public static void main(String[] args) throws InterruptedException
    {
        DebitCard initialVal = new DebitCard("Alex", 0);
        AtomicReference<DebitCard> debitCardRef = new AtomicReference<>(initialVal);
        DebitCard newValue = new DebitCard("Alex2", 10);
        DebitCard result = debitCardRef.accumulateAndGet(newValue, (prev, newVal) -> newVal);

        assert newValue == result;
        assert newValue == debitCardRef.get();

        Thread.currentThread().join();
    }
}
