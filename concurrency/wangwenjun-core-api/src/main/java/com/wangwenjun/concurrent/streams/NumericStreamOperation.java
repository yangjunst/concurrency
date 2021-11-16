package com.wangwenjun.concurrent.streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreamOperation
{
    public static void main(String[] args)
    {
        simpleTest();
    }

    private static void simpleTest()
    {
        assert IntStream.of(1, 2, 3, 4, 5).sum() == 15;
        assert IntStream.of(1, 2, 3, 4, 5).max().getAsInt() == 5;

        IntStream.of(1, 2, 3, 4, 5).parallel().forEach(System.out::println);

        IntStream parallelStream = IntStream.of(1, 2, 3, 4, 5).parallel();

        parallelStream.filter(i -> i > 1).map(i -> i * 10)
                .sequential()
                .forEach(System.out::println);

    }
}