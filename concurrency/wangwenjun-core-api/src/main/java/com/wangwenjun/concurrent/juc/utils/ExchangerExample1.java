package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ExchangerExample1
{
    public static void main(String[] args)
    {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() ->
        {
            System.out.println(currentThread() + " start.");
            try
            {
                randomSleep();
                String data = exchanger.exchange("I am from T1");
                System.out.println(currentThread() + " received: " + data);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(currentThread() + " end.");
        }, "T1").start();

        new Thread(() ->
        {
            System.out.println(currentThread() + " start.");
            try
            {
                randomSleep();
                String data = exchanger.exchange("I am from T2");
                System.out.println(currentThread() + " received: " + data);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(currentThread() + " end.");
        }, "T2").start();
    }

    private static void randomSleep()
    {
        try
        {
            TimeUnit.SECONDS.sleep(current().nextInt(10));
        } catch (InterruptedException e)
        {
            //ignore
        }
    }
}
