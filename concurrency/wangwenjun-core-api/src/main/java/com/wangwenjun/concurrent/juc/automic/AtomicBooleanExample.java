package com.wangwenjun.concurrent.juc.automic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample
{
    public static void main(String[] args)
    {
        //无参构造AtomicBoolean ，默认为false
        AtomicBoolean ab = new AtomicBoolean();
        assert !ab.get();
//前值依然为false
        assert !ab.getAndSet(true);
//更新后的结果为true
        assert ab.get();
    }
}
