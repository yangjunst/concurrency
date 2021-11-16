package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerExample3
{
    //add the jvm arguments -ea to enable the assertions feature.
    public static void main(String[] args)
            throws InterruptedException
    {
        final Exchanger<String> exchanger = new Exchanger<>();
//        new Thread(() ->
//        {
//            try
//            {
//                assert exchanger.exchange(null) == null : "the exchanged data not null";
//            } catch (InterruptedException e)
//            {
//            }
//        }).start();
//        assert exchanger.exchange(null) == null : "the exchanged data not null";

//        Thread t = new Thread(() ->
//        {
//            try
//            {
//                exchanger.exchange(null);
//            } catch (InterruptedException e)
//            {
//                System.out.println("An interrupt signal was caught");
//            }
//        });
//        t.start();
//        TimeUnit.SECONDS.sleep(1);
//        t.interrupt();

//        Thread t = new Thread(() ->
//        {
//            try
//            {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e)
//            {
//            }
//
//            try
//            {
//                exchanger.exchange(null);
//            } catch (InterruptedException e)
//            {
//                System.out.println("An interrupt signal was caught");
//            }
//        });
//        t.start();
//        TimeUnit.SECONDS.sleep(1);
//        t.interrupt();

        Thread t = new Thread(() ->
        {
            String s = "";
            for (int i = 0; i < 10000; i++)
            {
                s += "exchanger";
            }

            try
            {
                exchanger.exchange(s);
            } catch (InterruptedException e)
            {
                System.out.println("An interrupt signal was caught");
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}