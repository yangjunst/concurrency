package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class RecursiveTaskSum extends RecursiveTask<Long>
{
    private final long[] numbers;
    private final int startIndex;
    private final int endIndex;
    private static final long THRESHOLD = 10_000L;

    public RecursiveTaskSum(long[] numbers)
    {
        this(numbers, 0, numbers.length);
    }

    private RecursiveTaskSum(long[] numbers, int startIndex, int endIndex)
    {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Long compute()
    {
        int length = endIndex - startIndex;
        if (length <= THRESHOLD)
        {
            long result = 0L;
            for (int i = startIndex; i < endIndex; i++)
            {
                result += numbers[i];
            }
            return result;
        }

        int tempEndIndex = startIndex + length / 2;
        RecursiveTaskSum firstTask = new RecursiveTaskSum(numbers, startIndex, tempEndIndex);
        firstTask.fork();

        RecursiveTaskSum secondTask = new RecursiveTaskSum(numbers, tempEndIndex, endIndex);
        secondTask.fork();
        Long secondTaskResult = secondTask.join();
        Long firstTaskResult = firstTask.join();

        return (secondTaskResult + firstTaskResult);
    }

    public static void main(String[] args)
    {
        long[] numbers = LongStream.rangeClosed(1, 9_000_123).toArray();
        RecursiveTaskSum forkJoinSum = new RecursiveTaskSum(numbers);
        Long sum = ForkJoinPool.commonPool().invoke(forkJoinSum);
        System.out.println(sum);
        System.out.println(LongStream.rangeClosed(1, 9_000_123).sum());
    }
}
