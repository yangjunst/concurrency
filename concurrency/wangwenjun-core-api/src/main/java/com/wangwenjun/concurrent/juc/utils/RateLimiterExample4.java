package com.wangwenjun.concurrent.juc.utils;

import static java.lang.Thread.currentThread;

public class RateLimiterExample4
{

    private static final RateLimiterTokenBucket tokenBucket = new RateLimiterTokenBucket();

    public static void main(String[] args)
    {
        for (int i = 0; i < 20; i++)
        {
            new Thread(() ->
            {
                while (true)
                {
                    try
                    {
                        tokenBucket.bookOrder(prodID -> System.out.println("User: " + currentThread() + " book the prod order and prodID:" + prodID));
                    } catch (RateLimiterTokenBucket.NoProductionException e)
                    {
                        System.out.println("all of production already sold out.");
                        break;
                    } catch (RateLimiterTokenBucket.OrderFailedException e)
                    {
                        System.out.println("User: " + currentThread() + " book order failed, will try again.");
                    }
                }
            }).start();
        }
    }
}
