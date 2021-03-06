package com.wangwenjun.concurrent.metrics.healthcheck;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.util.concurrent.TimeUnit;

public class RESTfulServiceHealthCheckExample
{
    public static void main(String[] args) throws InterruptedException
    {
        final HealthCheckRegistry hcRegistry = new HealthCheckRegistry();
        hcRegistry.register("restful-hc", new RESTfulServiceHealthCheck());

        final MetricRegistry registry = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .build();
        registry.gauge("restful-hc", () -> hcRegistry::runHealthChecks);
        reporter.start(10, TimeUnit.SECONDS);
        Thread.currentThread().join();
    }
}
