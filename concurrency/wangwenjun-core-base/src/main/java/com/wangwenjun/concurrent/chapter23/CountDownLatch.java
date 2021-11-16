package com.wangwenjun.concurrent.chapter23;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch {
    private final Runnable runnable;

    public CountDownLatch(int limit) {
        this(limit, null);
    }

    public CountDownLatch(int limit, Runnable runnable) {
        super(limit);
        this.runnable = runnable;
    }

    @Override
    public void await()
            throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }

        if (null != runnable) {
            runnable.run();
        }
    }

    @Override
    public void await(TimeUnit unit, long time)
            throws InterruptedException, TimeoutException {
        if (time <= 0)
            throw new IllegalArgumentException("The time is invalid.");
        final long endMillis = System.currentTimeMillis() + unit.toMillis(time);
        synchronized (this) {
            while (limit > 0) {
                if (time <= 0)
                    throw new TimeoutException("The wait time over specify time.");
                this.wait(unit.toMillis(time));
                System.out.println("--------->wait over....");
                time = endMillis - System.currentTimeMillis();
            }
        }

        if (null != runnable) {
            runnable.run();
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0)
                throw new IllegalStateException("all of task already arrived");
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }
}
