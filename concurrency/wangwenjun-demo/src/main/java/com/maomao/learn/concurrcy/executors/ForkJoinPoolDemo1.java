package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/26 9:07
 *********************************************/
public class ForkJoinPoolDemo1 extends RecursiveTask<Long> {
    public static void main(String[] args) {
        ForkJoinPool pool1 = ForkJoinPool.commonPool();
        long[] longs = LongStream.rangeClosed(1, 100_0).toArray();
        Long result = pool1.invoke(new ForkJoinPoolDemo1(longs));
        System.out.println(result);

    }

    private int startIndex;
    private int endIndex;
    private long[] numbers;
    private final int THRESHOLD=10;

    public ForkJoinPoolDemo1(long[] numbers) {
        this(0,numbers.length,numbers);
    }

    public ForkJoinPoolDemo1(int startIndex, int endIndex, long[] numbers) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.numbers = numbers;
    }

    @Override
    protected Long compute() {
        int length=endIndex-startIndex;
        if(length<=THRESHOLD){
            long sum=0;
            for(int i=startIndex;i<endIndex;i++){
                sum+=numbers[i];
            }
            return sum;
        }
        int tempEndIndex=startIndex+length/2;
        ForkJoinPoolDemo1 task1=new ForkJoinPoolDemo1(startIndex,tempEndIndex,numbers);
        task1.fork();
        ForkJoinPoolDemo1 task2=new ForkJoinPoolDemo1(tempEndIndex,endIndex,numbers);
        task2.fork();
        return task2.join()+task1.join();
    }
}
