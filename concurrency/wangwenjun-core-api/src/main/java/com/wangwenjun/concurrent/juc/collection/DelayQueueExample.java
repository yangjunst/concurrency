package com.wangwenjun.concurrent.juc.collection;

import org.quartz.Scheduler;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample
{
    public static void main(String[] args) throws InterruptedException
    {
        simpleTest();
    }

    private static void take(){

    }

    private static void pollWithTimeout() throws InterruptedException
    {
        DelayQueue<DelayedEntry> delayQueue = new DelayQueue<>();
//        assert delayQueue.poll(10, TimeUnit.MILLISECONDS) == null;
        delayQueue.put(new DelayedEntry("A", 10 * 1000L));
        delayQueue.put(new DelayedEntry("B", 5 * 1000L));

//        assert delayQueue.poll(10, TimeUnit.MILLISECONDS) == null;
        TimeUnit.SECONDS.sleep(5);
        System.out.println(delayQueue.poll(6,TimeUnit.SECONDS));
        System.out.println(delayQueue.poll(6,TimeUnit.SECONDS));

//        assert delayQueue.poll(10, TimeUnit.MILLISECONDS).getValue().equals("B");
    }

    private static void poll() throws InterruptedException
    {
        DelayQueue<DelayedEntry> delayQueue = new DelayQueue<>();
        assert delayQueue.poll() == null;

        delayQueue.put(new DelayedEntry("A", 10 * 1000L));
        delayQueue.put(new DelayedEntry("B", 5 * 1000L));

        assert delayQueue.poll() == null;
        TimeUnit.SECONDS.sleep(5);

        assert delayQueue.poll().getValue().equals("B");
    }


    private static void peek()
    {
        DelayQueue<DelayedEntry> delayQueue = new DelayQueue<>();
        assert delayQueue.peek() == null;

        delayQueue.put(new DelayedEntry("A", 10 * 1000L));
        delayQueue.put(new DelayedEntry("C", 5 * 1000L));
        delayQueue.put(new DelayedEntry("B", 4 * 1000L));
//        assert delayQueue.peek().getValue().equals("B");
        System.out.println(delayQueue.peek());
    }

    private static void remaining() throws InterruptedException {
        DelayQueue<DelayedEntry> delayQueue = new DelayQueue<>();
//        assert delayQueue.size() == 0;
        System.out.println(delayQueue.size());
        System.out.println(delayQueue.remainingCapacity()==Integer.MAX_VALUE);
//        assert delayQueue.remainingCapacity() == Integer.MAX_VALUE;
        delayQueue.put(new DelayedEntry("A", 10 * 1000L));
        delayQueue.put(new DelayedEntry("B", 5 * 1000L));
        System.out.println(delayQueue.size());
        System.out.println(delayQueue.remainingCapacity()==Integer.MAX_VALUE);
        delayQueue.poll(5,TimeUnit.SECONDS);
        System.out.println(delayQueue.size());
        System.out.println(delayQueue.remainingCapacity()==Integer.MAX_VALUE);

//        assert delayQueue.size() == 2;
//        assert delayQueue.remainingCapacity() == Integer.MAX_VALUE;

    }

    private static void simpleTest() throws InterruptedException
    {
        DelayQueue<DelayedEntry> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedEntry("A", 10 * 1000L));
        delayQueue.put(new DelayedEntry("B", 5 * 1000L));
        final long timestamp = System.currentTimeMillis();
//        System.out.println(delayQueue.poll());
//        assert delayQueue.poll() == null;
        System.out.println(delayQueue.take());
        System.out.println(System.currentTimeMillis()-timestamp);
        System.out.println(delayQueue.take());
        System.out.println(System.currentTimeMillis()-timestamp);
        System.out.println(delayQueue.size());
//        DelayedEntry value = delayQueue.take();
//        assert value.getValue().equals("B");
//        assert (System.currentTimeMillis() - timestamp) >= 5_000L;
//
//        value = delayQueue.take();
//        assert value.getValue().equals("A");
//        assert (System.currentTimeMillis() - timestamp) >= 10_000L;
    }

    private static class DelayedEntry implements Delayed
    {
        private final String value;
        private final long time;

        private DelayedEntry(String value, long delayTime)
        {
            this.value = value;
            this.time = delayTime + System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit)
        {
            long delta = time - System.currentTimeMillis();
            return unit.convert(delta, TimeUnit.MILLISECONDS);
        }


        public String getValue()
        {
            return value;
        }

        @Override
        public int compareTo(Delayed o)
        {
            if (this.time < ((DelayedEntry) o).time)
            {
                return -1;
            } else if (this.time > ((DelayedEntry) o).time)
            {
                return 1;
            } else
                return 0;
        }

        @Override
        public String toString()
        {
            return "DelayedEntry{" +
                    "value='" + value + '\'' +
                    ", time=" + time +
                    '}';
        }
    }
}
