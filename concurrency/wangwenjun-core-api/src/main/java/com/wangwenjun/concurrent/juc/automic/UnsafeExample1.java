package com.wangwenjun.concurrent.juc.automic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeExample1
{
    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException
    {
        Example example1 = new Example();
        assert example1.getX() == 10;
        Example example2 = Example.class.newInstance();
        assert example2.getX() == 10;
        Example example3 = (Example) getUnsafe().allocateInstance(Example.class);
        assert example3.getX() == 0;
    }

    static class Example
    {
        private int x;

        public Example()
        {
            this.x = 10;
        }

        private int getX()
        {
            return x;
        }
    }

    static Unsafe getUnsafe()
    {
        try
        {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e)
        {
            throw new RuntimeException("can't initial the unsafe instance.", e);
        }
    }
}
