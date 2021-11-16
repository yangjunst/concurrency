package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceExample2
{
    public static void main(String[] args)
    {
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("Hello", 1);
        assert !reference.compareAndSet("Hello", "World", 2, 3);

        assert reference.compareAndSet("Hello", "World", 1, 2);

        assert reference.getReference().equals("World");
    }
}
