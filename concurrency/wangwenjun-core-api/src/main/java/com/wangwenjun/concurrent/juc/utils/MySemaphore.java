package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class MySemaphore extends Semaphore
{

    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    public MySemaphore(int permits)
    {
        super(permits);
    }

    public MySemaphore(int permits, boolean fair)
    {
        super(permits, fair);
    }

    @Override
    public void acquire() throws InterruptedException
    {
        super.acquire();
        this.queue.add(currentThread());
    }

    @Override
    public void acquireUninterruptibly()
    {
        super.acquireUninterruptibly();
        this.queue.add(currentThread());
    }

    @Override
    public boolean tryAcquire()
    {
        final boolean acquired = super.tryAcquire();
        if (acquired)
        {
            this.queue.add(currentThread());
        }
        return acquired;
    }

    @Override
    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException
    {
        final boolean acquired = super.tryAcquire(timeout, unit);
        if (acquired)
        {
            this.queue.add(currentThread());
        }
        return acquired;
    }

    @Override
    public void release()
    {
        final Thread currentThread = currentThread();
        if (!this.queue.contains(currentThread))
            return;

        super.release();
        this.queue.remove(currentThread);
    }

    @Override
    public void acquire(int permits) throws InterruptedException
    {
        super.acquire(permits);
        this.queue.add(currentThread());
    }

    @Override
    public void acquireUninterruptibly(int permits)
    {
        super.acquireUninterruptibly(permits);
        this.queue.add(currentThread());
    }

    @Override
    public boolean tryAcquire(int permits)
    {
        boolean acquired = super.tryAcquire(permits);
        if (acquired)
        {
            this.queue.add(currentThread());
        }
        return acquired;
    }

    @Override
    public boolean tryAcquire(int permits, long timeout, TimeUnit unit)
            throws InterruptedException
    {
        boolean acquired = super.tryAcquire(permits, timeout, unit);
        if (acquired)
        {
            this.queue.add(currentThread());
        }
        return acquired;
    }

    @Override
    public void release(int permits)
    {
        final Thread currentThread = currentThread();
        if (!this.queue.contains(currentThread))
            return;

        super.release(permits);
        this.queue.remove(currentThread);
    }

    public static void main(String[] args)
    {
        ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();
        queue.add(currentThread());
        queue.add(currentThread());
        queue.add(currentThread());
        System.out.println(queue);
        queue.remove(currentThread());
        System.out.println(queue);
    }
}