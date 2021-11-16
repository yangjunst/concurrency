package com.mmall.concurrency.example.atomic;

import java.lang.reflect.Field;

public class Unsafe {

    public native int getIntVolatile(Object obj, long offset);

    public final long getAndIncrement() {
        long valueOffset = 0xfff;
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }

    public final long getAndAddInt(Object obj, long offset, int var4) {
        int var6;
        do {
            var6 = this.getIntVolatile(obj, offset);
        } while (!this.compareAndSwapInt(obj, offset, var6, var6 + var4));

        return var6;
    }

    public native boolean compareAndSwapInt(Object obj, long offset,
                                            int expect, int update);


    private static Unsafe unsafe = new Unsafe();

    private Unsafe() {
    }

    public static Unsafe getUnsafe() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
            sm.checkPropertiesAccess();
        return unsafe;
    }

    public native long objectFieldOffset(Field field);

    public native boolean compareAndSwapLong(Object obj, long offset,
                                             long expect, long update);

    public native boolean compareAndSwapObject(Object obj, long offset,
                                               Object expect, Object update);

    public native void putOrderedInt(Object obj, long offset, int value);

    public native void putOrderedLong(Object obj, long offset, long value);

    public native void putOrderedObject(Object obj, long offset, Object value);

    public native void putIntVolatile(Object obj, long offset, int value);

    public native void putLongVolatile(Object obj, long offset, long value);

    public native void putLong(Object obj, long offset, long value);

    public native long getLongVolatile(Object obj, long offset);

    public native long getLong(Object obj, long offset);

    public native void putObjectVolatile(Object obj, long offset, Object value);

    public native void putObject(Object obj, long offset, Object value);

    public native Object getObjectVolatile(Object obj, long offset);

    public native int arrayBaseOffset(Class arrayClass);

    public native int arrayIndexScale(Class arrayClass);

    public native void unpark(Thread thread);

    public native void park(boolean isAbsolute, long time);
}