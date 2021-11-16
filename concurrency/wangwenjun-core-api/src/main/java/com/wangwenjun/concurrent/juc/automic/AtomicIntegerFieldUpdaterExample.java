package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterExample
{
    static class Alex
    {
        volatile int salary;

        public int getSalary()
        {
            return this.salary;
        }
    }

    public static void main(String[] args)
    {
        AtomicIntegerFieldUpdater<Alex> updater = AtomicIntegerFieldUpdater.newUpdater(Alex.class, "salary");
        Alex alex = new Alex();
        int result = updater.addAndGet(alex, 1);
        assert result == 1;
    }
}
