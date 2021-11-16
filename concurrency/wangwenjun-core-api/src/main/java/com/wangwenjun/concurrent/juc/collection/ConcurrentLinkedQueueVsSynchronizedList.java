package com.wangwenjun.concurrent.juc.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;


@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class ConcurrentLinkedQueueVsSynchronizedList
{

    private SynchronizedLinkedList synchronizedList;
    private ConcurrentLinkedQueue<String> concurrentLinkedQueue;
    private final static String DATA = "TEST";
    private final static Object LOCK = new Object();

    private static class SynchronizedLinkedList
    {
        private LinkedList<String> list = new LinkedList<>();

        void addLast(String element)
        {
            synchronized (LOCK)
            {
                list.addLast(element);
            }
        }

        String removeFirst()
        {
            synchronized (LOCK)
            {
                if (list.isEmpty()) return null;
                return list.removeFirst();
            }
        }
    }

    @Setup(Level.Iteration)
    public void setUp()
    {
        synchronizedList = new SynchronizedLinkedList();
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }


    @Group("sync")
    @Benchmark
    @GroupThreads(10)
    public void synchronizedListAdd()
    {
        synchronizedList.addLast(DATA);
    }

    @Group("sync")
    @Benchmark
    @GroupThreads(5)
    public String synchronizedListGet()
    {
        return synchronizedList.removeFirst();
    }

    @Group("concurrent")
    @Benchmark
    @GroupThreads(10)
    public void concurrentLinkedQueueAdd()
    {
        concurrentLinkedQueue.offer(DATA);
    }

    @Group("concurrent")
    @Benchmark
    @GroupThreads(5)
    public String concurrentLinkedQueueGet()
    {
        return concurrentLinkedQueue.poll();
    }

    public static void main(String[] args) throws RunnerException
    {
        final Options opt = new OptionsBuilder()
                .include(ConcurrentLinkedQueueVsSynchronizedList.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
