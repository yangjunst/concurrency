package com.wangwenjun.concurrent.juc.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ArrayBlockingQueueExample
{
    public static void main(String[] args)
    {
        produceAndConsume();
    }

    private static void produceAndConsume()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        IntStream.rangeClosed(0, 10).boxed().map(i -> new Thread("P-Thread-" + i)
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        String data = String.valueOf(System.currentTimeMillis());
                        queue.put(data);
                        System.out.println(currentThread() + " produce data: " + data);
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                    } catch (InterruptedException e)
                    {
                        System.out.println("Received the interrupt SIGNAL.");
                        break;
                    }
                }
            }
        }).forEach(Thread::start);

        IntStream.rangeClosed(0, 10).boxed().map(i -> new Thread("C-Thread-" + i)
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        String data = queue.take();
                        System.out.println(currentThread() + " consume data: " + data);
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                    } catch (InterruptedException e)
                    {
                        System.out.println("Received the interrupt SIGNAL.");
                        break;
                    }
                }
            }
        }).forEach(Thread::start);
    }

    private static void testPoll()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        assert queue.offer("first");
        assert queue.offer("second");
        assert queue.poll().equals("first");
        assert queue.poll().equals("second");
        assert queue.poll() == null;
    }

    private static void testPeek()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        assert queue.offer("first");
        assert queue.offer("second");
        assert queue.peek().equals("first");
        assert queue.peek().equals("first");
        queue.clear();
        assert queue.peek() == null;
    }


    private static void testTake()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        assert queue.offer("first");
        assert queue.offer("second");
        try
        {
            assert queue.take().equals("first");
            assert queue.take().equals("second");
            queue.take();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private static void testPollWithTimeout()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        assert queue.offer("first");
        assert queue.offer("second");
        try
        {
            assert queue.poll(10, TimeUnit.SECONDS).equals("first");
            assert queue.poll(10, TimeUnit.SECONDS).equals("second");
            assert queue.poll(10, TimeUnit.SECONDS) == null;
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private static void testOffer()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        assert queue.offer("first");
        assert queue.offer("second");
        assert !queue.offer("third");
        assert queue.size() == 2;
    }

    private static void testAdd()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        assert queue.add("first");
        assert queue.add("second");
        try
        {
            queue.add("third");
        } catch (Exception e)
        {
            assert e instanceof IllegalStateException;
        }
    }

    private static void testPut()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        try
        {
            queue.put("first");
            queue.put("second");
            //
            queue.put("third");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private static void testOffWithTimeout()
    {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        try
        {
            queue.offer("first", 10, TimeUnit.SECONDS);
            queue.offer("second", 10, TimeUnit.SECONDS);
            //
            queue.offer("third", 10, TimeUnit.SECONDS);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
