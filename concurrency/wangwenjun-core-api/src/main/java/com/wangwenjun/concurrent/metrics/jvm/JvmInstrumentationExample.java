package com.wangwenjun.concurrent.metrics.jvm;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;

import java.util.concurrent.TimeUnit;

public class JvmInstrumentationExample
{
    private final static MetricRegistry registry = new MetricRegistry();
    private final static ConsoleReporter reporter = ConsoleReporter
            .forRegistry(registry)
            .build();

    public static void main(String[] args) throws InterruptedException
    {
        reporter.start(0, 10, TimeUnit.SECONDS);
        registry.registerAll(new GarbageCollectorMetricSet());
        registry.registerAll(new ThreadStatesGaugeSet());
        registry.registerAll(new ClassLoadingGaugeSet());
        Thread.currentThread().join();
    }
}
