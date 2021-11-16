package com.maomao.learn.concurrcy.collection;

import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: DelayQueueDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/31 14:38
 *********************************************/
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayElement<String>> queue=new DelayQueue<>();
        queue.put(DelayElement.of(1000,"Java1"));
        queue.put(DelayElement.of(8000,"Java2"));
        queue.put(DelayElement.of(2000,"Java3"));
        queue.put(DelayElement.of(5000,"Java4"));
        Iterator<DelayElement<String>> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getE());
        }


    }

    static class DelayElement<E> implements Delayed {

        private final long expiredTime;
        private final E e;

        public DelayElement(long delay, E e) {
            this.expiredTime = delay + System.currentTimeMillis();
            this.e = e;
        }

        public static <T> DelayElement of(long delay,T t){
            return new DelayElement(delay,t);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expiredTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayElement element=(DelayElement)o;
            return this.expiredTime-element.expiredTime>0?1:-1;
        }

        public E getE() {
            return e;
        }
    }
}
