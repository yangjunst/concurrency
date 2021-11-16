package com.wangwenjun.concurrent.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.ClassloaderProfiler;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample23
{

    private byte[] alexBytes;

    private AlexClassLoader classLoader;

    @Setup
    public void init() throws IOException
    {
        this.alexBytes = Files.readAllBytes(
                Paths.get("C:\\Users\\wangwenjun\\IdeaProjects\\java-concurrency-book2\\target\\classes\\Alex.class")
        );
        this.classLoader = new AlexClassLoader(alexBytes);
    }

    @Benchmark
    public Object testLoadClass()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException
    {
        Class<?> alexClass = Class.forName("Alex", true, classLoader);
        return alexClass.newInstance();
    }

    public static void main(String[] args) throws RunnerException
    {
        final Options opts = new OptionsBuilder()
                .include(JMHExample23.class.getSimpleName())
                .addProfiler(ClassloaderProfiler.class)
                .build();
        new Runner(opts).run();
    }
}