package com.wangwenjun.concurrent.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)
@Warmup(iterations = 2)
public class JMHExample03
{

    @Benchmark
    public void test() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Measurement(iterations = 10)
    @Warmup(iterations = 5)
    @Benchmark
    public void test2() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException
    {
        final Options opts = new OptionsBuilder()
                .include(JMHExample03.class.getSimpleName())
                .forks(1)
//                .measurementIterations(5) //度量执行的批次为5,也就是
//                // 说在这五次中对基准方法执行与调用将会纳入统计
//                .warmupIterations(3)    //在真正的度量之前，首先会对代码进行3个批次的热身
//                //使代码的运行达到JVM已经优化的效果
                .build();
        new Runner(opts).run();
    }
}