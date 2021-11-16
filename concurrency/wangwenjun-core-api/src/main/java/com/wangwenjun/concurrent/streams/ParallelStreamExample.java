package com.wangwenjun.concurrent.streams;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Warmup(iterations = 20)
@Measurement(iterations = 20)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class ParallelStreamExample
{

    public static void main(String[] args)
            throws RunnerException
    {
        final Options opt = new OptionsBuilder()
                .include(ParallelStreamExample.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void calculateNormal(Blackhole hole)
    {
        long sum = 0L;
        for (long l = 0; l < 10_000_000; l++)
        {
            sum += l;
        }
        hole.consume(sum);
    }

    @Benchmark
    public void calculateStream(Blackhole hole)
    {
        long sum = LongStream.range(0, 10_000_000)
                .reduce(0L, Long::sum);
        hole.consume(sum);
    }

    @Benchmark
    public void calculateParallelStream(Blackhole hole)
    {
        long sum = LongStream.range(0, 10_000_000)
                .parallel()
                .reduce(0L, Long::sum);
        hole.consume(sum);

        List<Integer> integers = Arrays.asList(1, 2, 3);
        Spliterator<Integer> spliterator = integers.spliterator();

    }
}
