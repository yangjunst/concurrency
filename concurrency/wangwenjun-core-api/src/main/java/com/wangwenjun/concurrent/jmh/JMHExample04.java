package com.wangwenjun.concurrent.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)
@Warmup(iterations = 2)
public class JMHExample04
{

    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public void testAverageTime() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void testThroughput() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }


    @BenchmarkMode(Mode.SampleTime)
    @Benchmark
    public void testSampleTime() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Warmup(iterations = 5)
    @BenchmarkMode(Mode.SingleShotTime)
    @Benchmark
    public void testSingleShotTime() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Benchmark
    public void testThroughputAndAverageTime() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode(Mode.All)
    @Benchmark
    public void testAll() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException
    {
        final Options opts = new OptionsBuilder()
                .include(JMHExample04.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opts).run();
    }
}