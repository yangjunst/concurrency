package com.wangwenjun.juc.collections.blocking.demo;

import com.wangwenjun.juc.collections.blocking.DelayQueueExample;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 8:55
 * Program Goal:
 *********************************************/
public class DelayQueueDemo {
    public static long getDelay(TimeUnit unit,long expireTime) {
        long diff =  expireTime- System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
    public static void main(String[] args) {
        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();
        delayQueue.add(DelayElement.of("Delayed1", 1000));
        delayQueue.add(DelayElement.of("Delayed2", 800));
        delayQueue.add(DelayElement.of("Delayed3", 11000));
        delayQueue.add(DelayElement.of("Delayed4", 20000));
        System.out.println(delayQueue.poll());
        System.out.println(delayQueue.remove());

    }
    static class DelayElement<E> implements Delayed {

        private final E e;

        private final long expireTime;

        DelayElement(E e, long delay) {
            this.e = e;
            this.expireTime = System.currentTimeMillis() + delay;
        }

        static <T> DelayElement<T> of(T e, long delay) {
            return new DelayElement<>(e, delay);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expireTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayedObject) {
            DelayElement that = (DelayElement) delayedObject;
            if (this.expireTime < that.getExpireTime()) {
                return -1;
            } else if (this.expireTime > that.getExpireTime()) {
                return 1;
            } else {
                return 0;
            }
        }

        public E getData() {
            return e;
        }

        public long getExpireTime() {
            return expireTime;
        }
    }
}
