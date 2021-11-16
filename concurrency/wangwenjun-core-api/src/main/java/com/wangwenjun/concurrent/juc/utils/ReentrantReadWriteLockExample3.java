package com.wangwenjun.concurrent.juc.utils;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Measurement(iterations = 10)
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ReentrantReadWriteLockExample3
{
    @State(Scope.Group)
    public static class Test
    {
        private int x = 10;
        private final Lock lock = new ReentrantLock();
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();

        public void lockInc()
        {
            lock.lock();
            try
            {
                x++;
            } finally
            {
                lock.unlock();
            }
        }

        public int lockGet()
        {
            lock.lock();
            try
            {
                return x;
            } finally
            {
                lock.unlock();
            }
        }

        public void writeLockInc()
        {
            writeLock.lock();
            try
            {
                x++;
            } finally
            {
                writeLock.unlock();
            }
        }

        public int readLockGet()
        {
            readLock.lock();
            try
            {
                return x;
            } finally
            {
                readLock.unlock();
            }
        }

        public void synInc()
        {
            synchronized (this)
            {
                x++;
            }
        }

        public int syncGet()
        {
            synchronized (this)
            {
                return x;
            }
        }
    }

    @GroupThreads(5)
    @Group("lock")
    @Benchmark
    public void lockInc(Test test)
    {
        test.lockInc();
    }

    @GroupThreads(5)
    @Group("lock")
    @Benchmark
    public void lockGet(Test test, Blackhole blackhole)
    {
        blackhole.consume(test.lockGet());
    }

    @GroupThreads(5)
    @Group("rwlock")
    @Benchmark
    public void writeLockInc(Test test)
    {
        test.writeLockInc();
    }

    @GroupThreads(5)
    @Group("rwlock")
    @Benchmark
    public void readLockGet(Test test, Blackhole blackhole)
    {
        blackhole.consume(test.readLockGet());
    }


    @GroupThreads(5)
    @Group("sync")
    @Benchmark
    public void syncInc(Test test)
    {
        test.synInc();
    }

    @GroupThreads(5)
    @Group("sync")
    @Benchmark
    public void syncGet(Test test, Blackhole blackhole)
    {
        blackhole.consume(test.syncGet());
    }

    public static void main(String[] args)
            throws RunnerException
    {
        Options opts = new OptionsBuilder()
                .include(ReentrantReadWriteLockExample3.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opts).run();
    }
}