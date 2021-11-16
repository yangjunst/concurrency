package com.wangwenjun.concurrent.juc.automic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static com.wangwenjun.concurrent.juc.automic.UnsafeExample1.getUnsafe;

public class UnsafeExample2
{
    public static void main(String[] args)
            throws NoSuchFieldException
    {
        Guard guard = new Guard();
        assert !guard.canAccess(10);
        assert guard.canAccess(1);

        Unsafe unsafe = getUnsafe();
        Field f = guard.getClass().getDeclaredField("accessNo");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 20);
        assert guard.canAccess(20);
    }

    static class Guard
    {
        private int accessNo = 1;

        public boolean canAccess(int no)
        {
            return this.accessNo == no;
        }
    }
}
