package com.maomao.learn.java8;
import sun.misc.Unsafe;
import java.lang.reflect.Field;
/********************************************
 * 文件名称: UnsafeDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/16 9:19
 *********************************************/
public class UnsafeDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException, InstantiationException {
//        ExecutorService service = Executors.newFixedThreadPool(10000);
////        Counter counter = new CasCounter();
////        long start = System.currentTimeMillis();
////        for (int i = 0; i < 1000; i++) {
////            service.submit(new CounterRunnable(counter, 10000));
////        }
////        service.shutdown();
////        service.awaitTermination(1, TimeUnit.MINUTES);
////        long end = System.currentTimeMillis();
////        System.out.println("counter result:" + counter.getCounter());
////        System.out.println("Time passed in ms:" + (end - start));
        Unsafe unsafe=getUnsafe();
        Instance o = (Instance)unsafe.allocateInstance(Instance.class);
        Integer i = o.getI();
        System.out.println(i);
    }

    private static class Instance{
        private Integer i=10;
        public Integer getI() {
            return i;
        }
        public Instance(){
            System.out.println("constructor...");
        }
    }

    interface Counter {
        void increment();
        long getCounter();
    }

    private static class CounterRunnable implements Runnable {
        private final Counter counter;
        private final int num;
        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("error");
        }
    }

    static class CasCounter implements Counter {

        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offset;

        public CasCounter() throws NoSuchFieldException {
            this.unsafe = getUnsafe();
            this.offset = this.unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while (!unsafe.compareAndSwapLong(this, offset, current, current + 1)) {
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }
}
