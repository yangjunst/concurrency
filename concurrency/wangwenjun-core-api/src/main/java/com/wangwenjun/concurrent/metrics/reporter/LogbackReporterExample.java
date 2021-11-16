package com.wangwenjun.concurrent.metrics.reporter;

import com.codahale.metrics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

public class LogbackReporterExample
{
    private final static Logger logger = LoggerFactory.getLogger(LogbackReporterExample.class);
    private final static MetricRegistry registry = new MetricRegistry();
    private final static Counter totalBusiness = new Counter();
    private final static Counter successBusiness = new Counter();
    private final static Counter failBusiness = new Counter();
    private final static Timer timer = new Timer();
    private final static Histogram volumeHisto = new Histogram(new ExponentiallyDecayingReservoir());

    private final static Slf4jReporter reporter = Slf4jReporter.forRegistry(registry)
            .outputTo(LoggerFactory.getLogger("com.metrics.wangwenjun"))
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private final static RatioGauge successGauge = new RatioGauge()
    {
        @Override
        protected Ratio getRatio()
        {
            return Ratio.of(successBusiness.getCount(), totalBusiness.getCount());
        }
    };

    static
    {
        registry.register("cloud-disk-upload-total", totalBusiness);
        registry.register("cloud-disk-upload-success", successBusiness);
        registry.register("cloud-disk-upload-failure", failBusiness);
        registry.register("cloud-disk-upload-frequency", timer);
        registry.register("cloud-disk-upload-volume", volumeHisto);
        registry.register("cloud-disk-upload-suc-rate", successGauge);
    }

    public static void main(String[] args)
    {
        reporter.start(10, TimeUnit.SECONDS);
        while (true)
        {
            upload(new byte[current().nextInt(10_000)]);
        }
    }

    private static void upload(byte[] buffer)
    {
        totalBusiness.inc();
        Timer.Context context = timer.time();
        try
        {
            int x = 1 / current().nextInt(10);
            TimeUnit.MILLISECONDS.sleep(200);
            volumeHisto.update(buffer.length);
            successBusiness.inc();
        } catch (Exception e)
        {
            failBusiness.inc();
        } finally
        {
            context.close();
        }
    }
}
