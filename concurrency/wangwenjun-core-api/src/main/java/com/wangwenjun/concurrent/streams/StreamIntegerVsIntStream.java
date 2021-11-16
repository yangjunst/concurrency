package com.wangwenjun.concurrent.streams;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Warmup(iterations = 20)
@Measurement(iterations = 20)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class StreamIntegerVsIntStream
{

    private Stream<Integer> integerStream;

    private IntStream intStream;

    @Setup(Level.Invocation)
    public void init()
    {
        this.integerStream = IntStream.range(0, 100).boxed();
        this.intStream = IntStream.range(0, 100);
    }

    @Benchmark
    public void streamIntegerReduce(Blackhole hole)
    {
        int result = this.integerStream
                .map((Integer i) -> i * 10)
                .reduce(0, (Integer a, Integer b) ->
                {
                    return a + b;
                });
        hole.consume(result);
    }

    @Benchmark
    public void streamIntegerUnboxThenReduce(Blackhole hole)
    {
        int result = integerStream
                .mapToInt(Integer::intValue)
                .map((int i) -> i * 10)
                .reduce(0, (int a, int b) ->
                {
                    return a + b;
                });
        hole.consume(result);
    }

    @Benchmark
    public void intStreamReduce(Blackhole hole)
    {
        int result = intStream
                .map((int i) -> i * 10)
                .reduce(0, (int a, int b) ->
                {
                    return a + b;
                });
        hole.consume(result);
    }


    public static void main(String[] args) throws RunnerException
    {
        final Options opt = new OptionsBuilder()
                .include(StreamIntegerVsIntStream.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
