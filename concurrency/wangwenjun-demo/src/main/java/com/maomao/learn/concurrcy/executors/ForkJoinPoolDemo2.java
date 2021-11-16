package com.maomao.learn.concurrcy.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/26 10:15
 *********************************************/
public class ForkJoinPoolDemo2 extends RecursiveAction{
    private List<Long> numbers;
    private int startIndex;
    private int endIndex;
    private int factor;
    private final int THRESHOLD=10;

    public ForkJoinPoolDemo2(List<Long> numbers, int startIndex, int endIndex, int factor) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.factor = factor;
    }

    public static void main(String[] args) {
        List<Long> list=new ArrayList<>();
        for(int i=0;i<1000;i++){
            Long random = ThreadLocalRandom.current().nextLong(10_000);
            list.add(random);
        }
        list.stream().forEach(e-> System.out.print(e+","));
        System.out.println();
        ForkJoinPool forkJoinPool=ForkJoinPool.commonPool();
        forkJoinPool.invoke(new ForkJoinPoolDemo2(list,0,list.size(),10));
        list.stream().forEach(e-> System.out.print(e+","));
    }

    @Override
    protected void compute() {
        int length=endIndex-startIndex;
        if(length<=THRESHOLD){
            computeDirectly();
        }else {
            int tempEndIndex=startIndex+length/2;
            ForkJoinPoolDemo2 fork1=new ForkJoinPoolDemo2(numbers,startIndex,tempEndIndex,factor);
            ForkJoinPoolDemo2 fork2=new ForkJoinPoolDemo2(numbers,tempEndIndex,endIndex,factor);
            invokeAll(fork1,fork2);
      /*      int middle=(startIndex+endIndex)/2;
            ForkJoinPoolDemo2 fork1=new ForkJoinPoolDemo2(numbers,startIndex,middle,factor);
            ForkJoinPoolDemo2 fork2=new ForkJoinPoolDemo2(numbers,middle,endIndex,factor);
            invokeAll(fork1,fork2);*/
        }
    }

  /*  private void computeDirectly(int length) {
        for(int i=0;i<length;i++){
            numbers.set(i,numbers.get(i)*factor);
        }
    }*/

    private void computeDirectly() {
        for(int i=startIndex;i<endIndex;i++){
            numbers.set(i,numbers.get(i)*factor);
        }
    }
}
