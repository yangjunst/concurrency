package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayExample
{
    public static void main(String[] args)
    {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        AtomicIntegerArray intAtomicArr = new AtomicIntegerArray(intArray);
        assert intAtomicArr.addAndGet(1, 10) == 12;
        assert intAtomicArr.get(1) == 12;
    }
}
