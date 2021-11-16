package com.wangwenjun.concurrent.juc.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 20)
@Measurement(iterations = 20)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Group)
public class LinkedTransferQueueVsLinkedBlockingQueue
{

    public static class OptQueue
    {
        LinkedTransferQueue<Long> transferQueue = new LinkedTransferQueue<>();
        LinkedBlockingQueue<Long> linkedQueue = new LinkedBlockingQueue<>();
    }

    private OptQueue optQueue;

    @Setup(Level.Iteration)
    public void setUp()
    {
        this.optQueue = new OptQueue();
    }

    @GroupThreads(5)
    @Group("transferQueue")
    @Benchmark
    public void transferQueueInsertion()
    {
        optQueue.transferQueue.put(System.currentTimeMillis());
    }

    @GroupThreads(5)
    @Group("transferQueue")
    @Benchmark
    public long transferQueueDeletion() throws InterruptedException
    {
        return optQueue.transferQueue.take();
    }

    @GroupThreads(5)
    @Group("linkedQueue")
    @Benchmark
    public void linkedQueueInsertion() throws InterruptedException
    {
        optQueue.linkedQueue.put(System.currentTimeMillis());
    }

    @GroupThreads(5)
    @Group("linkedQueue")
    @Benchmark
    public long linkedQueueDeletion() throws InterruptedException
    {
        return optQueue.linkedQueue.take();
    }

    public static void main(String[] args) throws RunnerException
    {
        final Options opt = new OptionsBuilder()
                .include(LinkedTransferQueueVsLinkedBlockingQueue.class.getSimpleName())
                .timeout(TimeValue.seconds(10))
                .build();
        new Runner(opt).run();
    }
}
