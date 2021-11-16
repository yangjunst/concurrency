package com.wangwenjun.concurrent.juc.collection;

import java.util.concurrent.SynchronousQueue;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

public class SynchronousQueueExample
{
    public static void main(String[] args) throws InterruptedException
    {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        IntStream.rangeClosed(0, 1).forEach(i ->
                new Thread(() ->
                {
                    try
                    {
                        queue.put(currentThread().getName());
                        System.out.println(currentThread() + " put element " + currentThread().getName());
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }).start()
        );

        IntStream.rangeClosed(0, 1).forEach(i ->
                new Thread(() ->
                {
                    try
                    {
                        String value = queue.take();
                        System.out.println(currentThread() + " take " + value);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }).start()
        );
    }
}
